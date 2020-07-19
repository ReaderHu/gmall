package com.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.gmall.bean.*;
import com.gmall.manager.mapper.*;
import com.gmall.service.SkuService;
import com.gmall.util.RedisUtil;
import jodd.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.UUID;

/**
 * ClassName SkuServiceImpl
 * PackageName com.gmall.manager.service.impl
 *
 * @Description
 * @auther wang
 * @create 2020-07-07
 */
@Service
public class SkuServiceImpl implements SkuService {


    @Autowired
    private PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    private PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    private PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    private PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据商品id获取商品信息
     * @param skuId
     * @return
     */
    public PmsSkuInfo getSkuByIdFromDB(String skuId) {

        // 根据id获取sku
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);

        // 获取图片集
        PmsSkuImage pmsSkuImage =new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);

        skuInfo.setSkuImageList(pmsSkuImages);
        return skuInfo;
    }


    /**
     * 根据商品id获取商品信息
     * @param skuId
     * @param remoteAddr
     * @return
     */
    @Override
    public PmsSkuInfo getSkuById(String skuId, String remoteAddr) {

        System.out.println("ip 为" +remoteAddr + "的同学" +Thread.currentThread().getName() + "进入商品详情");

        // 根据id获取sku
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();

        // 连接缓存
        Jedis jedis = redisUtil.getJedis();
        String skuKey = "sku:" + skuId + ":info";
        // 查询缓存
        String skuJson = jedis.get(skuKey);
        if (StringUtils.isNotBlank(skuJson)) {
            System.out.println("ip 为" +remoteAddr + "的同学" +Thread.currentThread().getName() + "从缓存中获取商品详情");
            pmsSkuInfo = JSON.parseObject(skuJson,PmsSkuInfo.class);
        } else {
            String skuLockKey = "sku:" + skuId + ":lock";
            System.out.println("ip 为" +remoteAddr + "的同学" +Thread.currentThread().getName() + "从数据库中获取商品详情，lock：" +  skuLockKey );
            // 缓存没有查询数据库
            // 设置分布式锁
            String token = UUID.randomUUID().toString();
            String OK = jedis.set(skuLockKey,token,"nx","px",10*1000);// 拿到锁的线程有10秒的过期
            if (StringUtils.isNotBlank(OK)&& OK.equals("OK")) {

                pmsSkuInfo = getSkuByIdFromDB(skuId);
                if (pmsSkuInfo != null) {
                    jedis.setex(skuKey, 60*10,JSON.toJSONString(pmsSkuInfo));

                } else {
                    //数据库中sku不存在
                    //为了防止缓存穿透，null或者空字符串设置个redis
                    jedis.setex(skuKey,60*3, JSON.toJSONString(""));
                }

                // 访问数据库成功后删除锁
                String lockToken = jedis.get(skuLockKey);
                if (StringUtils.isNotBlank(lockToken)&& lockToken.equals(token)) {
                    //jedis.eval("lua");可与用lua脚本，在查询到key的同时删除该key，防止高并发下的意外的发生
                    jedis.del(skuLockKey);
                    System.out.println("ip 为" +remoteAddr + "的同学" +Thread.currentThread().getName() + "拿到数据，删除锁" );
                }
            } else {
                System.out.println("ip 为" +remoteAddr + "的同学" +Thread.currentThread().getName() + "没有拿到锁开始自旋" );
                // 设置失败,自旋（该线程睡眠几秒后重新访问）
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 不加return 是重新开了一个线程，
                // 加return 后为同一个线程，从头开始调用方法
                return getSkuById(skuId, remoteAddr);

            }
        }

        jedis.close();

        return pmsSkuInfo;
    }

    /**
     * 根据spuid获取当前sku的spu的其他sku的集合的hash表
     * @param productId
     * @return
     */
    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {

        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.getSkuSaleAttrValueListBySpu(productId);

        return pmsSkuInfos;
    }

    /**
     * 保存sku信息
     * @param pmsSkuInfo
     * @return
     */
    @Override
    public String saveSkuInfo(PmsSkuInfo pmsSkuInfo) {

        // 保存sku信息
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);

        // 插入平台属性关联
        for (PmsSkuAttrValue pmsSkuAttrValue : pmsSkuInfo.getSkuAttrValueList()) {

            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        // 插入销售属性关联
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : pmsSkuInfo.getSkuSaleAttrValueList()) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }

        // 插入图片信息
        for (PmsSkuImage pmsSkuImage : pmsSkuInfo.getSkuImageList()) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());

            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }


        return "success";
    }


}

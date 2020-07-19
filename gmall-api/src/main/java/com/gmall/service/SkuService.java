package com.gmall.service;

import com.gmall.bean.PmsSkuInfo;

import java.util.List;

/**
 * ClassName SkuService
 * PackageName com.gmall.service
 *
 * @Description
 * @auther wang
 * @create 2020-07-07
 */
public interface SkuService {


    /**
     * 保存sku信息
     * @param pmsSkuInfo
     * @return
     */
    String saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    /**
     * 根据商品id获取商品信息
     * @param skuId
     * @param remoteAddr
     * @return
     */
    PmsSkuInfo getSkuById(String skuId, String remoteAddr);

    /**
     * 根据spuid获取当前sku的spu的其他sku的集合的hash表
     * @param productId
     * @return
     */
    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);
}

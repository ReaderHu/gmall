package com.gmall.manager.mapper;

import com.gmall.bean.PmsSkuInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * ClassName PmsSkuInfoMapper
 * PackageName com.gmall.manager.mapper
 *
 * @Description
 * @auther wang
 * @create 2020-07-07
 */
public interface PmsSkuInfoMapper extends Mapper<PmsSkuInfo> {
    /**
     * 根据spuid获取当前sku的spu的其他sku的集合的hash表
     * @param productId
     * @return
     */
    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);
}

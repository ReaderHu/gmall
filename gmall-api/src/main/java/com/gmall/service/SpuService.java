package com.gmall.service;

import com.gmall.bean.PmsProductImage;
import com.gmall.bean.PmsProductInfo;
import com.gmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * ClassName SpuService
 * PackageName com.gmall.service
 *
 * @Description
 * @auther wang
 * @create 2020-07-05
 */
public interface SpuService {

    /**
     * 根据三级分类id获取商品spu（具体的商品）
     * @param catalog3Id
     * @return
     */
    List<PmsProductInfo> spuList(String catalog3Id);

    /**
     * 保存SPU方法
     * @param pmsProductInfo
     * @return
     */
    String saveSpuInfo(PmsProductInfo pmsProductInfo);

    /**
     * 根据商品id获取商品属性方法
     * @param spuId
     * @return
     */
    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    /**
     * 根据spuId获取商品图片
     * @param spuId
     * @return
     */
    List<PmsProductImage> spuImageList(String spuId);

    /**
     * 获取销售属性列表
     * @param productId
     * @param skuId
     * @return
     */
    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId, String skuId);
}

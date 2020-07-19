package com.gmall.manager.mapper;

import com.gmall.bean.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * ClassName PmsProductSaleAttrMapper
 * PackageName com.gmall.manager.mapper
 *
 * @Description
 * @auther wang
 * @create 2020-07-05
 */
public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {

    //  获取spu销售属性
    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") String productId,@Param("skuId") String skuId);
}

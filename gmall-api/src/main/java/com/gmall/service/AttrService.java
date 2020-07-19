package com.gmall.service;

import com.gmall.bean.PmsBaseAttrInfo;
import com.gmall.bean.PmsBaseAttrValue;
import com.gmall.bean.PmsBaseSaleAttr;

import java.util.List;
import java.util.Set;

/**
 * ClassName AttrService
 * PackageName com.gmall.manager.service
 *
 * @Description
 * @auther wang
 * @create 2020-07-05
 */
public interface AttrService {

    /**
     * 获取所有的属性
     * @param catalog3Id
     * @return
     */
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    /**
     * 保存或者修改属性
     * @param pmsBaseAttrInfo
     * @return
     */
    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    /**
     * 根据属性id 获取属性所有的值
     * @param attrId 属性id
     * @return
     */
    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    /**
     * 获取平台定义的销售属性
     * @return
     */
    List<PmsBaseSaleAttr> baseSaleAttrList();

    List<PmsBaseAttrInfo> getAttrValueListByValueId(Set<String> valueIdSet);
}

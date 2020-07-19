package com.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gmall.bean.PmsBaseAttrInfo;
import com.gmall.bean.PmsBaseAttrValue;
import com.gmall.bean.PmsBaseSaleAttr;
import com.gmall.manager.mapper.PmsBaseAttrInfoMapper;
import com.gmall.manager.mapper.PmsBaseAttrValueMapper;
import com.gmall.manager.mapper.PmsBaseSaleAttrMapper;
import com.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * ClassName AttrServiceImpl
 * PackageName com.gmall.manager.service
 *
 * @Description
 * @auther wang
 * @create 2020-07-05
 */
@Service
public class AttrServiceImpl implements AttrService {


    // 属性Mapper
    @Autowired
    private PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    private PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Autowired
    private PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    /**
     * 获取所有的属性
     *
     * @param catalog3Id
     * @return
     */
    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {

        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);


        for (PmsBaseAttrInfo baseAttrInfo : pmsBaseAttrInfos) {
            List<PmsBaseAttrValue> attrValueList = new ArrayList<>();
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(baseAttrInfo.getId());
            // 获取属性的值
            attrValueList = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);

            baseAttrInfo.setAttrValueList(attrValueList);
        }

        return pmsBaseAttrInfos;
    }

    /**
     * 保存或修改属性
     *
     * @param pmsBaseAttrInfo
     * @return
     */
    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {

        try {

            if (StringUtils.isEmpty(pmsBaseAttrInfo.getId())) {
                // 不存在，保存场合
                // 插入属性
                pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);

                // 插入属性的值
                for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrInfo.getAttrValueList()) {
                    // 设置属性ID
                    pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                    pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
                }
            } else {
                // ID不为空，修改的场合
                // 设置修改条件
                Example example = new Example(PmsBaseAttrInfo.class);
                // property : "id' 跟PmsBaseAttrInfo对象的属性一样
                example.createCriteria().andEqualTo("id", pmsBaseAttrInfo.getId());

                pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo, example);

                // 按照属性id删除所有的属性值
                PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
                pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.delete(pmsBaseAttrValueDel);

                // 重新插入所有的属性值
                for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrInfo.getAttrValueList()) {
                    pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                    pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
                }
            }
        } catch (Exception e) {
            return "false";
        }
        return "success";
    }

    /**
     * 根据属性id 获取属性所有的值
     * @param attrId 属性id
     * @return
     */
    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {

        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);

        List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);

        return pmsBaseAttrValues;
    }

    /**
     * 获取平台定义的销售属性
     * @return
     */
    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {

        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = pmsBaseSaleAttrMapper.selectAll();

        return pmsBaseSaleAttrs;
    }

    @Override
    public List<PmsBaseAttrInfo> getAttrValueListByValueId(Set<String> valueIdSet) {
        return null;
    }
}

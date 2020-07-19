package com.gmall.service;

import com.gmall.bean.PmsBaseCatalog1;
import com.gmall.bean.PmsBaseCatalog2;
import com.gmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * ClassName CatalogService
 * PackageName com.gmall.service
 *
 * @Description
 * @auther wang
 * @create 2020-07-05
 */
public interface CatalogService {

    /**
     * 查询一级分类
     * @return
     */
    List<PmsBaseCatalog1> getCatalog1();

    /**
     * 查询二级分类
     * @return
     */
    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    /**
     * 查询三级分类
     * @return
     */
    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);



}

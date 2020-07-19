package com.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gmall.bean.PmsProductImage;
import com.gmall.bean.PmsProductInfo;
import com.gmall.bean.PmsProductSaleAttr;
import com.gmall.manager.utils.PmsUploadUtil;
import com.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName SpuController
 * PackageName com.gmall.manager.controller
 *
 * @Description
 * @auther wang
 * @create 2020-07-05
 */
@Controller
@CrossOrigin
public class SpuController {

    @Reference
    private SpuService spuService;

    /**
     * 根据三级分类id获取商品spu（具体的商品）
     * @param catalog3Id
     * @return
     */
    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id) {

        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }

    /**
     * 保存SPU方法
     * @param pmsProductInfo
     * @return
     */
    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {

        String success = spuService.saveSpuInfo(pmsProductInfo);

        return success;
    }

    /**
     * 根据商品id获取商品属性方法
     * @param spuId
     * @return
     */
    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){

        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);

        return pmsProductSaleAttrs;
    }

    /**
     * 上传图片，返回文件存储位置
     * @param multipartFile
     * @return
     */
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file")MultipartFile multipartFile){

        // 调用上传文件共同方法
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile);
        return imgUrl;
    }

    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId) {

        // 获取spu的图片
        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);


        return pmsProductImages;
    }


}

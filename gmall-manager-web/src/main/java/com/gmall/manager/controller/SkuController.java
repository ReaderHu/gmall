package com.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gmall.bean.PmsSkuInfo;
import com.gmall.service.SkuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName SkuController
 * PackageName com.gmall.manager.controller
 *
 * @Description
 * @auther wang
 * @create 2020-07-07
 */
@Controller
@CrossOrigin
public class SkuController {


    @Reference
    private SkuService skuService;

    @RequestMapping("saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo) {

        // 产品spuid
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());

        String result = skuService.saveSkuInfo(pmsSkuInfo);

        return "success";
    }



}

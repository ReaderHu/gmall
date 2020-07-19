package com.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gmall.bean.UmsMember;
import com.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName UserController
 * PackageName com.gmall.user.controller
 *
 * @Description
 * @auther wang
 * @create 2020-06-28
 */
@Controller
public class UserController {


    @Reference
    private UserService userService;

    /**
     * 获取用户所有信息
     * @return 用户信息List
     */
    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser(){
        List<UmsMember> allUser = userService.getAllUser();

        return allUser;
    }


}

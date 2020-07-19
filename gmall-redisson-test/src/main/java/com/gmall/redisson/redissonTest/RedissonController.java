package com.gmall.redisson.redissonTest;

import com.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

/**
 * ClassName RedissonController
 * PackageName com.gmall.manager.redissonTest
 *
 * @Description
 * @auther wang
 * @create 2020-07-12
 */
@Controller
public class RedissonController {

    @Autowired
    RedisUtil redisUtil;


    @Autowired
    RedissonClient redissonClient;


    @RequestMapping("redissontest")
    @ResponseBody
    public String test() {

        Jedis jedis = redisUtil.getJedis();

        RLock lock = redissonClient.getLock("lock");


        String v = jedis.get("k");
        if(StringUtils.isBlank(v)) {
            v = "1";
        }
        jedis.set("k",Integer.parseInt(v)+1+"");
        System.out.println(v);
        jedis.close();





        return "success";
    }


}

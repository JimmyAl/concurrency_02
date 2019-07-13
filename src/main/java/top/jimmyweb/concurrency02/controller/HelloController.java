package top.jimmyweb.concurrency02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jimmyweb.concurrency02.Entity.User;
import top.jimmyweb.concurrency02.redis.RedisUtil;

import java.util.Date;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/20 0020
 */
@Controller
@RequestMapping("/ms")
public class HelloController {

    @Autowired
    private RedisUtil redisUtil;

    private static int ExpireTime = 60;

    @RequestMapping("/redis/set")
    @ResponseBody
    public boolean helloRedis(String key,String value){
        User userEntity =new User();
        userEntity.setId(Long.valueOf(1));
        userEntity.setGuid(String.valueOf(1));
        userEntity.setName("zhangsan");
        userEntity.setAge(String.valueOf(20));
        userEntity.setCreateTime(new Date());
        return redisUtil.set(key,value);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("/redis/expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }

}

package com.houde;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
@RestController
public class HelloController {


    @Value("${cupSize}")
    private String size;

    //注入的配置
    @Value("${age}")
    private int age;

    @Resource
    private GirlProperties girlProperties;

    @RequestMapping(value = {"/hi", "/hello"}, method = RequestMethod.GET)
    public String say(@RequestParam(value = "name", required = false,defaultValue = "houde") String name) {
        //@RequestParam获取?后的参数
        return "Hi spring boot cupSize is " + size + " age is " + age + " name:" + name;
    }

    @GetMapping("/girl/{id}")
    public String girl(@PathVariable("id") Integer id) {
        return "girl " + girlProperties + "  id=" + id;
    }
}

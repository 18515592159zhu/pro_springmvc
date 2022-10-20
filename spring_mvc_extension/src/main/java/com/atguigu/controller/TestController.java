package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zhuchifeng
 * @Date 2022/10/20 6:09
 * @Version 1.0
 */
@Controller
public class TestController {
    @RequestMapping("/test/hello")
    public String testHello() {
        System.out.println(1 / 0);
        return "success";
    }
}

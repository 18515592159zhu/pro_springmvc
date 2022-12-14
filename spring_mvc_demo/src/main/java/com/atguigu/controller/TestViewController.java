package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringMVC的视图
 * SpringMVC中的视图是View接口，视图的作用渲染数据，将模型Model中的数据展示给用户
 * SpringMVC视图的种类很多，默认有转发视图和重定向视图
 * 当工程引入jstl的依赖，转发视图会自动转换为JstlView
 * 若使用的视图技术为Thymeleaf，在SpringMVC的配置文件中配置了Thymeleaf的视图解析器，由此视图解析器解析之后所得到的是ThymeleafView
 * 1、ThymeleafView
 * 当控制器方法中所设置的视图名称没有任何前缀时，此时的视图名称会被SpringMVC配置文件中所配置的视图解析器解析，视图名称拼接视图前缀和视图
 * 后缀所得到的最终路径，会通过转发的方式实现跳转
 * 2、转发视图
 * SpringMVC中默认的转发视图是InternalResourceView
 * SpringMVC中创建转发视图的情况：
 * 当控制器方法中所设置的视图名称以"forward:"为前缀时，创建InternalResourceView视图，此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析
 * 而是会将前缀"forward:"去掉，剩余部分作为最终路径通过转发的方式实现跳转，例如"forward:/"，"forward:/employee"
 * 3、重定向视图
 * SpringMVC中默认的重定向视图是RedirectView
 * 当控制器方法中所设置的视图名称以"redirect:"为前缀时，创建RedirectView视图，此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析
 * 而是会将前缀"redirect:"去掉，剩余部分作为最的视图解析器解析，而是会将前缀"redirect:"去掉，剩余部分作为最终路径通过重定向的方式实现跳转，例如"redirect:/"，"redirect:/employee"
 * 4、视图控制器view-controller
 * 当控制器方法中，仅仅用来实现页面跳转，即只需要设置视图名称时，可以将处理器方法使用view-controller标签进行表示
 * 在springmvc.xml中进行配置
 * <mvc:view-controller path="/" view-name="index"></mvc:view-controller>
 */
@Controller
public class TestViewController {

    //1、ThymeleafView
    @RequestMapping("/testThymeleafView")
    public String testThymeleafView() {
        return "success";
    }

    //2、转发视图
    @RequestMapping("/testForwardView")
    public String testInternalResourceView() {
        return "forward:/testThymeleafView";
    }

    //3、重定向视图
    @RequestMapping("/testRedirectView")
    public String testRedirectView() {
        return "redirect:/testThymeleafView";
    }
}
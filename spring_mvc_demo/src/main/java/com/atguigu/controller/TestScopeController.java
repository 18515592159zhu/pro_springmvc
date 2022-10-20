package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 向域对象共享数据
 * 1、使用ServletAPI向request域对象共享数据
 * 2、使用ModelAndView向request域对象共享数据
 * 使用ModelAndView时，可以使用其Model功能向请求域共享数据
 * 使用View功能设置逻辑视图，但是控制器方法一定要将ModelAndView作为方法的返回值
 * 3、使用Model向request域对象共享数据
 * 4、使用map向request域对象共享数据
 * 5、使用ModelMap向request域对象共享数据
 * 其实在底层中，这些类型的形参最终都是通过BindingAwareModelMap创建
 * public class BindingAwareModelMap extends ExtendedModelMap {}
 * public class ExtendedModelMap extends ModelMap implements Model {}
 * public class ModelMap extends LinkedHashMap<String, Object> {}
 * 6、向session域共享数据
 * 7、向application域共享数据
 */
@Controller
public class TestScopeController {

    //1、使用ServletAPI向request域对象共享数据
    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request) {
        request.setAttribute("testRequestScope", "hello,ServletAPI");
        return "success";
    }

    //2、使用ModelAndView向request域对象共享数据
    @RequestMapping("/testModelAndView")
    public ModelAndView testModeAndView() {
        /**
         * ModelAndView有Model和View的功能
         * Model主要用于向请求域共享数据
         * View主要用于设置视图，实现页面跳转
         */
        ModelAndView modelAndView = new ModelAndView();
        //向请求域共享数据
        modelAndView.addObject("testModeAndViewRequestScope", "hello,ModelAndView");
        //设置视图，实现页面跳转
        modelAndView.setViewName("success");
        return modelAndView;
    }

    //3、使用Model向request域对象共享数据
    @RequestMapping("/testModel")
    public String testModel(Model model) {
        System.out.println(model.getClass().getName());//org.springframework.validation.support.BindingAwareModelMap
        model.addAttribute("testModelRequestScope", "hello,Model");
        return "success";
    }

    //4、使用map向request域对象共享数据
    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map) {
        System.out.println(map.getClass().getName());//org.springframework.validation.support.BindingAwareModelMap
        map.put("testMapRequestScope", "hello,Map");
        return "success";
    }

    //5、使用ModelMap向request域对象共享数据
    @RequestMapping("/testModelMap")
    public String testMap(ModelMap modelMap) {
        System.out.println(modelMap.getClass().getName());//org.springframework.validation.support.BindingAwareModelMap
        modelMap.addAttribute("testModelMapRequestScope", "hello,ModelMap");
        return "success";
    }

    //6、向session域共享数据
    @RequestMapping("/testSession")
    public String testSession(HttpSession session) {
        session.setAttribute("testSessionScope", "hello,Session");
        return "success";
    }

    //7、向application域共享数据
    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session) {
        ServletContext application = session.getServletContext();
        application.setAttribute("testApplicationScope", "hello,application");
        return "success";
    }
}

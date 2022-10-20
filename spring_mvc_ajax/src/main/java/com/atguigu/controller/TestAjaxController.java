package com.atguigu.controller;

import com.atguigu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1、@RequestBody：将请求体中的内容和控制器方法的形参进行绑定
 * 2、使用@RequestBody注解将json格式的请求参数转换为Java对象
 * a>导入jackson的依赖
 * b>在SpringMVC的配置文件中设置<mvc:annotation-driven />
 * c>在处理请求的控制器方法的形参位置，直接设置json格式的请求参数要转换的java类型的形参，使用@RequestBody注解标识即可
 * 3、@ResponseBody：将所标识的控制器方法的返回值作为响应报文的响应体响应到浏览器
 * 4、使用@ResponseBody注解响应浏览器json格式的数据
 * a>导入jackson的依赖
 * b>在SpringMVC的配置文件中设置<mvc:annotation-driven />
 * c>将需要转换为json字符串的java对象直接作为控制器方法的返回值，使用@ResponseBody注解标识控制器方法
 * 就可以将java对象直接转换为json字符串，并响应到浏览器
 * 常用的Java对象转换为json的结果：
 * 实体类-->json对象
 * map-->json对象
 * list-->json数组
 */
@Controller
//@RestController @Controller+@ResponseBody
public class TestAjaxController {

    /**
     * @RequestBody可以获取请求体信息 使用@RequestBody注解标识控制器方法的形参，当前请求的请求体就会为当前注解所标识的形参赋值
     */
    @RequestMapping(value = "/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody) {
        System.out.println("requestBody = " + requestBody);//requestBody = username=tom&password=666666
        return "success";
    }

    /**
     * @RequestBody获取json格式的请求参数 在使用了axios发送ajax请求之后，浏览器发送到服务器的请求参数有两种格式：
     * 1、name=value&name=value...，此时的请求参数可以通过request.getParameter()获取，对应SpringMVC中，可以直接通过控制器方法的形参获取此类请求参数
     * 2、{key:value,key:value,...}，此时无法通过request.getParameter()获取，之前我们使用操作json的相关jar包gson或jackson处理此类请求参数，可以将其转换为指定的实体类对象或map集合。
     * 在SpringMVC中，直接使用@RequestBody注解标识控制器方法的形参即可将此类请求参数转换为java对象
     */
    @RequestMapping("/test/ajax")
    public void testAjax(Integer id, @RequestBody String requestBody, HttpServletResponse response) throws IOException {
        System.out.println("requestBody:" + requestBody);//requestBody:{"username":"admin","password":"123456"}
        System.out.println("id:" + id);//id:1001
        response.getWriter().write("hello,axios");
    }

    @RequestMapping("/testRequestBodyJson")
    public void testRequestBodyJson(@RequestBody String requestBody, HttpServletResponse response) throws IOException {
        System.out.println("requestBody = " + requestBody);//requestBody = {"id":1001,"username":"admin","password":"123456","age":23,"gender":"男"}
        response.getWriter().write("hello requestBodyJson");
    }


    //将json格式的数据转换为map集合
    @RequestMapping("/testRequestBodyMap")
    public void testRequestBodyMap(@RequestBody Map<String, Object> map, HttpServletResponse response) throws IOException {
        System.out.println("map = " + map);//map = {id=1001, username=admin, password=123456, age=23, gender=男}
        response.getWriter().println("Hello requestBodyMap");
    }

    //将json格式的数据转换为实体类对象
    @RequestMapping("/testRequestBodyPojo")
    public void testRequestBodyPojo(@RequestBody User user, HttpServletResponse response) throws IOException {
        System.out.println("user = " + user);//user = User{id=1001, username='admin', password='123456', age=23, gender='男'}
        response.getWriter().print("Hello requestBodyPojo");
    }

    //=========================================================@ResponseBody=================================================
    @RequestMapping("/testNoUseResponseBody")
    public String testNoUseResponseBody() {
        //此时会跳转到逻辑视图success所对应的页面
        return "success";
    }

    /**
     * @ResponseBody用于标识一个控制器方法，可以将该方法的返回值直接作为响应报文的响应体响应到浏览器
     */
    @RequestMapping("/testUseResponseBody")
    @ResponseBody
    public String testUseResponseBody() {
        //此时响应浏览器数据success
        return "success";
    }

    /**
     * @ResponseBody响应浏览器json数据 响应浏览器list集合
     */
    @RequestMapping("/testResponseBodyJson")
    @ResponseBody
    public List<User> testResponseBodyJson() {
        User user1 = new User(1001, "admin1", "123456", 20, "男");
        User user2 = new User(1002, "admin2", "123456", 20, "男");
        User user3 = new User(1003, "admin3", "123456", 20, "男");
        List<User> list = Arrays.asList(user1, user2, user3);
        return list;
    }

    /**
     * @ResponseBody响应浏览器json数据 响应浏览器map集合
     */
    @RequestMapping("/testResponseBodyJsonMap")
    @ResponseBody
    public Map<String, Object> testResponseBodyJsonMap() {
        User user1 = new User(1001, "admin1", "123456", 20, "男");
        User user2 = new User(1002, "admin2", "123456", 20, "男");
        User user3 = new User(1003, "admin3", "123456", 20, "男");
        Map<String, Object> map = new HashMap<>();
        map.put("1001", user1);
        map.put("1002", user2);
        map.put("1003", user3);
        return map;
    }

    /**
     * @ResponseBody响应浏览器json数据 响应浏览器实体类对象
     */
    @RequestMapping("/testResponseBodyJsonPojo")
    @ResponseBody
    public User testResponseBodyJsonPojo() {
        User user = new User(1001, "admin", "123456", 20, "男");
        return user;
    }

    //@RestController注解是springMVC提供的一个复合注解，标识在控制器的类上，就相当于为类添加了@Controller注解
    //并且为其中的每个方法添加了@ResponseBody注解
}

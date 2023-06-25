package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "hello spring boot";
    }
    /*
    浏览器向服务器传参有两种方式，一是在通过get请求，在路径后加问号携带参数，如/xxx?id=1。
    另一种是通过post请求，在request请求体中携带表单中的参数，这种参数在路径上是看不到的。
    这两种方式所传的参数，在服务端都可以通过request.getParameter(参数名)这样的方式来获取。
    而@RequestParam注解，就相当于是request.getParameter()，是从request对象中获取参数的。
    有时，我们也愿意利用请求路径本身来传参，即将参数拼到路径里，如/xxx/1，这里的1就是参数，
    那么在解析路径的时候，也是能获取到这个参数的。而@PathVarible就是解析路径，从中获得对应级次的参数。
     */

    //  /student?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //  /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应html数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 20);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "北京大学");
        model.addAttribute("age", 120);
        return "/demo/view";
    }

    //响应json数据(异步请求)
    // Java对象->JSON字符串->JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 20);
        map.put("salary", 8000);
        return map;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String,Object>> lists = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 20);
        map.put("salary", 8000);
        lists.add(map);
        map = new HashMap<>();
        map.put("name", "王五");
        map.put("age", 20);
        map.put("salary", 8000);
        lists.add(map);
        map = new HashMap<>();
        map.put("name", "赵六");
        map.put("age", 20);
        map.put("salary", 8000);
        lists.add(map);
        return lists;
    }
}

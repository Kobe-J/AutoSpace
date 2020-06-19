package com.chapter.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@Api(value = "GET",description = "我的GET方法")
public class GetMethod {
    //返回给客户端cookies的get请求
    @GetMapping("/demo")
    @ApiOperation(value = "返回给客户端cookies的get请求",httpMethod = "GET")
    @ResponseBody
    public String demo(HttpServletResponse response){
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        System.out.println(cookie);
        return "success";
    }


    //一个需要携带cookies访问的get请求
    @GetMapping("/demo1")
    @ApiOperation(value = "一个需要携带cookies访问的get请求",httpMethod = "GET")
    @ResponseBody
    public String demo1(HttpServletRequest request){
        Cookie [] cookies = request.getCookies();
        if (cookies!=null) {
        for (Cookie cookie:cookies) {
            if (0!=cookie.getName().length()&&0!=cookie.getValue().length()) {
                if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                    System.out.println(cookie.getName());
                    System.out.println(cookie.getValue());
                    return "恭喜你访问成功";
                } else {
                    System.out.println(cookie.getName());
                    System.out.println(cookie.getValue());
                    return "cookies 参数错误";
                }
               } else {
                return "请携带完整的cookies信息";
            }
              }
            }else {
            return "请携带cookies访问";
        }
        return "success1";
    }
    //这是一个需要请求参数的  get 方法
    @RequestMapping(value = "/demo2" ,method = RequestMethod.GET)
    @ApiOperation(value = "这是一个需要请求参数的get方法",httpMethod = "GET")
    @ResponseBody
    public Map<Integer, String> demo2(@RequestParam Integer admin,
                                      @RequestParam Integer pwd){
            Map<Integer,String> map = new HashMap();
            map.put(2,"A");
            map.put(1,"B");
            System.out.println(map);
            return map;
    }

    @GetMapping("/demo3/{admin}/{pwd}")
    @ApiOperation(value = "这是一个需要请求参数的get方法",httpMethod = "GET")
    @ResponseBody
    public Map<Integer, String> demo3(@PathVariable Integer admin,
                                      @PathVariable Integer pwd){
        Map<Integer,String> map = new HashMap();
        map.put(1,"a");
        map.put(2,"b");
        System.out.println(map);
        return map;
    }
}

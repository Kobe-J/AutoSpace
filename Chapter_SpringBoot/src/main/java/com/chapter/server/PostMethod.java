package com.chapter.server;


import com.chapter.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(value = "Post",description = "我的POST方法")
public class PostMethod {

    @RequestMapping(value = "/post/demo1", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "登录成功返回cookies", httpMethod = "POST")
    public String demo1(HttpServletResponse httpResponse,
                        @RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        if (username.equals("") | password.equals("")) {
            return "用户或密码不允许为空";
        } else if (username.equals("admin") && password.equals("admin")) {
            Cookie cookies = new Cookie("login", "true");
            httpResponse.addCookie(cookies);
            return "login success!";
        }
        return "用户名或密码错误";
    }

    @RequestMapping(value = "/post/demo2", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "需携带cookies 请求用户信息列表",httpMethod = "POST")
    public String demo2(HttpServletRequest request,
                        @RequestBody User user) {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie:cookies) {
                if (0!=cookie.getName().length()&&0!=cookie.getValue().length()) {
                    if (cookie.getName().equals("login")
                            && cookie.getValue().equals("true")
                            && user.getUsername().equals("admin")
                            && user.getPassword().equals("admin")) {
                        user.setUsername("admin");
                        user.setPassword("admin");
                        user.setAge("23");
                        user.setLoc("测试");
                        user.setSex("男");
                        return user.toString();
                    } else {
                        System.out.println(cookie.getName());
                        System.out.println(cookie.getValue());
                        return "cookies 参数错误,或用户名密码错误";
                    }
                } else {
                    return "请携带完整的cookies信息";
                }
            }
        }
            return "请携带cookies访问";

    }

}
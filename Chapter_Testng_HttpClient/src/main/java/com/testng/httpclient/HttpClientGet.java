package com.testng.httpclient;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;


public class HttpClientGet {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void befortest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("Redirect.Url");
    }

    @Test
    public void test1() throws IOException {
        //声明测试接口路径
            HttpGet get = new HttpGet(url);
            //Httpclient初始化  获取cookies
            this.store = new BasicCookieStore();
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.store).build();
            //接受接口返回的信息
            CloseableHttpResponse response =  httpClient.execute(get);
            //返回的信息转字符串
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
            //获取cookies信息
            List<Cookie> cookieStores = store.getCookies();
            for (Cookie cookie:cookieStores) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
        }
    }
    //依赖test1
    @Test(dependsOnMethods = {"test1"})
    public void test2() throws IOException {
        HttpGet get = new HttpGet(bundle.getString("Cookies.Url"));
        DefaultHttpClient client = new DefaultHttpClient();
        client.setCookieStore(this.store);
        CloseableHttpResponse response = client.execute(get);
        String cookies = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(cookies);
    }

}

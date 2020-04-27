package com.testng.httpclient;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class HttpClientPost {

    private String url;
    private String url1;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void befortest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("Post.Url");
        url1 = bundle.getString("Redirect.Url");
    }
    @Test
    public void test1() throws IOException {
        //声明测试接口路径
        HttpGet get = new HttpGet(url1);
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
    @Test(dependsOnMethods = {"test1"})
    public void post() throws IOException {
        HttpPost post = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.store).build();
        post.setHeader("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "admin");
        jsonObject.put("pwd", "admin");
        StringEntity parm = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(parm);
        CloseableHttpResponse response = httpClient.execute(post);
        String resulf = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONObject responseJson = new JSONObject(resulf);
        System.out.println(responseJson);
        JSONObject js = (JSONObject) responseJson.get("cookies");
        if (0 != responseJson.get("loc").toString().length()  && 0 != js.get("kobe").toString().length()){
                 if (responseJson.get("loc").equals("Post请求测试成功")&&js.get("kobe").equals("gigi")){
                            System.out.println("执行成功");
                            System.out.println(resulf);
                            System.out.println(response);
                    } else{
                                System.out.println("执行失败返回值不对或cookie信息不匹配");
                            }
             }else{
            System.out.println(false);
            }
        }
    }


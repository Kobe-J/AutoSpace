package com.tester.cases;


import com.tester.config.TestConfig;
import com.tester.model.InterfaceName;
import com.tester.utils.ConfigFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.List;


/**
 * 2020.4.27
 * 学习CloseableHttpClient 4.5.12
 * */
public class test {

    @BeforeTest
    public void beforeTest(){
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
    }

    @Test
    public void test1() throws IOException {
        TestConfig.store = new BasicCookieStore();
        TestConfig.defaultHttpClient =HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName","yaoxilong");
        param.put("password","123456");
        post.setHeader("content-type","application/json");
        StringEntity stringEntity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(stringEntity);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        List<Cookie> cookies = TestConfig.store.getCookies();
        System.out.println(TestConfig.store);
        System.out.println(cookies);
    }

    @Test
    public void test2() throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName","yaoxilong");
        jsonObject.put("password","123456");
        post.setHeader("content-type","application/json");
        StringEntity stringEntity = new StringEntity(jsonObject.toString(),"utf-8");
        post.setEntity(stringEntity);
        HttpResponse response =defaultHttpClient.execute(post);
        String result;
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        TestConfig.store = defaultHttpClient.getCookieStore();
        System.out.println(TestConfig.store);
        System.out.println(result);
    }
}

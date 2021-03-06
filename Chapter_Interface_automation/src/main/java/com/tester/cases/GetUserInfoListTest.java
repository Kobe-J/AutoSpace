package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.GetUserListCase;
import com.tester.model.User;
import com.tester.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Date 2020.3.2
 * @Auto yxl
 * 获取用户列表信息接口自动化
 * */

public class GetUserInfoListTest {



    @Test(dependsOnGroups="loginTrue",description = "获取性别为男的用户信息")
    public void getUserListInfo() throws IOException, InterruptedException {
        //声明sql组件
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);
        //接口的代码
        JSONArray resultJson = getJsonResult(getUserListCase);
        Thread.sleep(2000);
        List<User> userList = session.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User u : userList){
            System.out.println("list获取的user:"+u.toString());
        }
        JSONArray userListJson = new JSONArray(userList);

        Assert.assertEquals(userListJson.length(),resultJson.length());
        for(int a = 0;a<resultJson.length();a++){
            JSONObject expect = (JSONObject) resultJson.get(a);
            JSONObject actual = (JSONObject) userListJson.get(a);
            Assert.assertEquals(expect.toString(), actual.toString());
        }


    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient=HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArray = new JSONArray(result);

        System.out.println("调用接口list result:"+result);

        return jsonArray;

    }

}

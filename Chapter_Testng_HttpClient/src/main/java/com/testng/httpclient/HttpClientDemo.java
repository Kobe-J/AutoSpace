package com.testng.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;


public class HttpClientDemo {
    @Test
    public void demo1() throws IOException {
        String resulf;

        String url="http://127.0.0.1:8002/redirect/demo";
        HttpGet get = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(get);
        resulf = EntityUtils.toString(response.getEntity());
        System.out.println(resulf);
        System.out.println(get.getMethod());
    }
}

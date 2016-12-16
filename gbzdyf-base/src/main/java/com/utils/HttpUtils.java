package com.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by y on 2016/12/16.
 */
public class HttpUtils {

    public static String httpGet(String httpUrl) {
        String result = "";
        DefaultHttpClient httpclient = new DefaultHttpClient();// 创建http客户端
        HttpGet httpget = new HttpGet(httpUrl);
        HttpResponse response = null;
        HttpParams params = httpclient.getParams(); // 计算网络超时用
        HttpConnectionParams.setConnectionTimeout(params, 15 * 1000);
        HttpConnectionParams.setSoTimeout(params, 20 * 1000);

        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();// 得到http的内容
            response.getStatusLine().getStatusCode();// 得到http的状态返回值
            result = EntityUtils.toString(response.getEntity());// 得到具体的返回值，一般是xml文件
            entity.consumeContent();// 如果entity不为空，则释放内存空间
            httpclient.getCookieStore();// 得到cookis
            httpclient.getConnectionManager().shutdown();// 关闭http客户端
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String httpPost(String httpUrl, String data) {
        String result = "";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(httpUrl);
        // httpclient.setCookieStore(DataDefine.mCookieStore);

        HttpParams params = httpclient.getParams(); // 计算网络超时用
        HttpConnectionParams.setConnectionTimeout(params, 15 * 1000);
        HttpConnectionParams.setSoTimeout(params, 20 * 1000);
        httpPost.setHeader("Content-Type", "text/xml");
        StringEntity httpPostEntity;

        try {
            httpPostEntity = new StringEntity(data, "UTF-8");
            httpPost.setEntity(httpPostEntity);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();// 得到http的内容
            response.getStatusLine().getStatusCode();// 得到http的状态返回值
            result = EntityUtils.toString(response.getEntity());// 得到具体的返回值，一般是xml文件
            entity.consumeContent();// 如果entity不为空，则释放内存空间
            httpclient.getCookieStore();// 得到cookis
            httpclient.getConnectionManager().shutdown();// 关闭http客户端
        } catch (Exception e) {
            e.printStackTrace();
        }// base64是经过编码的字符串，可以理解为字符串
        // StringEntity httpPostEntity = new StringEntity("UTF-8");
        return result;
    }
}

package com.sa.weilianGL.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * HttpClient处理工具类
 */
@Slf4j
public class HttpClientUtil {
    private static CloseableHttpClient httpClient;

    static {
        httpClient = HttpClients.createDefault();;
    }
    /**
     *  带参数的post请求
     *
     * @param url
     * @param body
     * @return
     */
    public static String sendPost(String url, String body) {
        String responseContent = null;
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(body,
                    StandardCharsets.UTF_8.toString()));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, StandardCharsets.UTF_8.toString());
            response.close();
        } catch (Exception e) {
            log.error("sendPost运行,异常:", e.getMessage(), e.getCause());

//            e.printStackTrace();
        }
        return responseContent;
    }




    //特殊的post请求
    public static String sendPostDataFile(String url, String body,int WorkSpaceId,String Access_Key) {
        String responseContent = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            //这个后期可能需要更改
            httpPost.setHeader("Access-Key",Access_Key);
            httpPost.addHeader("X-Prophet-Workspace-Id", String.valueOf(WorkSpaceId));
            httpPost.setEntity(new StringEntity(body, StandardCharsets.UTF_8.toString()));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, StandardCharsets.UTF_8.toString());
            response.close();
            httpClient.close();
        } catch (Exception e) {
//            log.error("sendPost运行,异常:", e.getMessage(), e.getCause());
//            e.printStackTrace();
        }
        return responseContent;
    }
    /**
     * Get请求
     *
     * @param url 请求URL
     * @return
     */
    public static String doGet(String url) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)
                    .setConnectionRequestTimeout(35000)
                    .setSocketTimeout(60000)
                    .build();
            httpGet.setConfig(requestConfig);

            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            log.error("doGet运行,ClientProtocolException异常:", e.getMessage(), e.getCause());
        } catch (IOException e) {
            log.error("doGet运行,IOException异常:", e.getMessage(), e.getCause());
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
                if (null != httpclient) {
                    httpclient.close();
                }
            } catch (IOException e) {
                log.error("doGet关闭,IOException异常:", e.getMessage(), e.getCause());
//                e.printStackTrace();
            }
        }
        return result;
    }



    public static String sendPost(String url,String key,String jsonStr){
        url =url+"?"+key+"="+jsonStr;
        //String url1 =url+"?"+key+"="+jsonStr;
        PrintWriter out =null;
        BufferedReader in = null;
        String result="";
        try {
            URL realUrl =new URL(url.replaceAll("\r|\n",""));
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestProperty("accept","application/json");
            conn.setRequestProperty("Conntent-Type","application/json");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null){
                result+=line;
            }
        }catch (Exception e){
            System.out.println(" post 请求出现异常： "+e);
            e.printStackTrace();
        }finally {
            out.close();
        }
        return result;
    }
}
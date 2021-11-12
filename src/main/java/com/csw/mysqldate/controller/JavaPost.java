/*
package com.csw.mysqldate.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpStatus;
import sun.net.www.http.HttpClient;

import java.util.HashMap;

import com.bing.constant.ResultModel;
import com.bing.model.Company;
import com.google.common.collect.Lists;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaPost {
    public static String sendPost(String url, final Map<String,String> head, final Company paemear) throws Exception{
        boolean isSuccess = false;
        String str="";
        HttpPost post = null;
        HttpClient httpClient = new DefaultHttpClient();
        // 设置超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);

        post = new HttpPost(url);
        // 构造消息头

        post.setHeader("Content-type", "application/json; charset=utf-8");
        for (Map.Entry<String,String> entry : head.entrySet()) {
            post.setHeader(entry.getKey(),entry.getValue());
        }
        // 构建消息实体
        // 发送Json格式的数据请求
        System.out.println(System.currentTimeMillis());
        HttpResponse response = httpClient.execute(post);
        System.out.println(System.currentTimeMillis());
        // 检验返回码
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode != HttpStatus.SC_OK){
            isSuccess = false;
        }else{
            int retCode = 0;
            */
/**
 * 读取服务器返回过来的json字符串数据
 * 并发量测试
 *
 * @param xian 线程池个数
 * @param count 每个线程的调用次数
 * @param url 调用的 URL
 * @param head 头参数
 * @param paemear 传入参数
 *//*

            str = EntityUtils.toString(response.getEntity());
        }
        if(post != null){
            try {
                post.releaseConnection();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    */
/**
 * 并发量测试
 * @param xian 线程池个数
 * @param count 每个线程的调用次数
 * @param url 调用的 URL
 * @param head 头参数
 * @param paemear 传入参数
 *//*

    public static void bing(final int xian,final int count, final String url, final Map<String,String> head, final Company paemear){

        for(int i = 1; i<xian; i++){
            final int m =i;
            Thread t = new Thread(new Runnable(){//线程启动
                public void run(){
                    try {
                        int j = 0;//成功次数
                        int k = 0; //失败次数
                        for (int l=1;l<count;l++){
                            System.out.println(m +"号线程");
                            String str = ss.sendPost(url,head,paemear);
                            System.out.println(str);
                            JSONObject jsonObject=JSONObject.fromObject(str);
                            ResultModel resultModel=(ResultModel)JSONObject.toBean(jsonObject, ResultModel.class);
                            if(resultModel.getError()==200){
                                j++;
                            }else{
                                k++;
                            }
                            System.out.println(m+"号线程正在调用："+l+"次");
                        }
                        System.out.println(m+"号线程共调应100次 成功："+j+"失败："+k);
                    }catch (Exception e){
                        System.out.println(m +"号线程查询出错");
                    }
                }
            });
            t.start();
        }
    }


    public static void main(String[] args){
        final Map<String,String> head = new HashMap<>();
        head.put("token","e7dd5368743457c51405802283d54057ca95f9c73a19fe54");
        head.put("terminalType","Android");
        Company paemear = new Company();
        bing(2,2,"http://192.168.2.203:8080/HouseLizardCloud/Company/addCompany",head,paemear);

    }
}
    */
/*public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        //准备转成jsonObject
        map.put("cabinet_id",cabinet_id);  //将对方需要传的必须参数进行添加到map集合

        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = ""; //请求对方的路径地址
        HttpPost post = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            StringEntity s = new StringEntity(JSONObject.toJSONString(map)); //JSONObject.toJSONString(map)将map集合转成JSONObject
            s.setContentEncoding("UTF-8");  //字符编码
            s.setContentType("application/json");
            post.setEntity(s);
            //post.addHeader("content-type", "text/xml");
            HttpResponse res = client.execute(post);
            String response = EntityUtils.toString(res.getEntity()); //将请求回来的json转成String
            System.out.println(response);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                jsonObject = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*//*


 */

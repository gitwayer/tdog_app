package com.tdog.tdog_app.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpHelper implements IHttpProcessor {

    private static HttpHelper mHttpHelper;

    private HttpHelper(){

    }

    public static HttpHelper getInstance(){
        if(mHttpHelper==null){
            synchronized (HttpHelper.class){
                if(mHttpHelper==null){
                    mHttpHelper = new HttpHelper();
                }
            }
        }
        return mHttpHelper;
    }

    private static IHttpProcessor mIHttpProcessor;

    public static void init(IHttpProcessor processor){
        mIHttpProcessor = processor;
    }

    @Override
    public void post(String url, Map<String, Object> map, ICallback callback) {
        String finalUrl = appendParams(url,map);
        mIHttpProcessor.post(finalUrl,map,callback);
    }

    public static String appendParams(String url,Map<String,Object> params){
        if(params==null||params.isEmpty()){
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        if(urlBuilder.indexOf("?")<=0){
            urlBuilder.append("?");
        }else{
            if(!urlBuilder.toString().endsWith("&")){
                urlBuilder.append("&");
            }
        }
        for(Map.Entry<String,Object> entry:params.entrySet()){
            urlBuilder.append("&"+entry.getKey()).append("=").append(encoder(entry.getValue().toString()));
        }
        return urlBuilder.toString();
    }

    public static String encoder(String value){
        try {
            return URLEncoder.encode(value,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw  new RuntimeException();
        }
    }
}

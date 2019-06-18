package com.tdog.tdog_app.net;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpProcessor implements IHttpProcessor{
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpProcessor() {
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler();
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallback callback) {
        RequestBody requestBody = appendBody(params);
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("error","error info: url:"+call.request().url()+", exception:"+e.toString());
                callback.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String result = response.body().toString();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });

                }
            }
        });
    }

    private RequestBody appendBody(Map<String,Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if(params==null||params.isEmpty()){
            return body.build();
        }
        for(Map.Entry<String,Object> entry:params.entrySet()){
            body.add(entry.getKey(),entry.getValue().toString());
        }
        return  body.build();
    }

}

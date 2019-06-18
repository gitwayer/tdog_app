package com.tdog.tdog_app.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallBack<Result> implements  ICallback{
    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> clazz = analysisClassInfo(this);
        Result objResult = null;
        try {
            objResult = (Result) gson.fromJson(result, clazz);
        }catch (JsonSyntaxException e){
            Log.e("error","gson转化异常 返回结果 result: "+result+" ,error: "+e.toString());
        }
        onSuccess(objResult);
    }

    private Class<?> analysisClassInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] actualType = ((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>) actualType[0];
    }

    public abstract void onSuccess(Result objResult);

    @Override
    public void onFailure() {

    }
}

package com.tdog.library;

import android.app.Activity;
import android.view.View;

import com.tdog.library.annotation.ContentView;
import com.tdog.library.annotation.EventBase;
import com.tdog.library.annotation.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectManager {

    public static void inject(Activity activity){
        injectLayout(activity);//注册布局
        injectView(activity);//注册view
        injectEvent(activity);//注册事件
    }

    private static void injectLayout(Activity activity) {
        Class<?> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if(contentView!=null){
            //第一种方式
            //activity.setContentView(contentView.value());
            //第二种方式
            try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.invoke(activity,contentView.value());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectView(Activity activity) {
        Class<?> clazz = activity.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
            InjectView findViewbyId = field.getAnnotation(InjectView.class);
            if(findViewbyId!=null){
                int viewId = findViewbyId.value();
                try {
                    Method method = clazz.getMethod("findViewById", int.class);
                    Object view = method.invoke(activity,viewId);
                    field.setAccessible(true);//设置权限
                    field.set(activity,view);//设置值
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     *     @OnClick({R.id.tv,R.id.btn})
     *     public void show(View view){
     *
     *     }
     * @param activity
     */
    private static void injectEvent(Activity activity){
        Class<?> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            //获取每个方法的注解
            Annotation[] annotations = method.getAnnotations();
            //循环遍历注解
            for (Annotation annotation : annotations) {
                //获取onClick注解上的注解类型
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if(annotationType!=null){
                    //获的eventBase的注解
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);//得到onCLick上的eventBase注解
                    String callBackListener = eventBase.callBack(); //onClick 方法名
                    String listenerSetter = eventBase.listenerSetter();// setOnClickListener 方法名
                    Class<?> listenerType = eventBase.listenerType();// View.OnClickListener.class 类类型

                    try {
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        int[] valueIDs = (int[]) valueMethod.invoke(annotation);//执行onClick注解上的value方法

                        ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                        handler.addMethodMap(callBackListener,method);
                        Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType},handler);

                        //R.id.btn  R.id.tv
                        for (int valueID : valueIDs) {
                            View view = activity.findViewById(valueID);//得到view
                            Method setterMethod = view.getClass().getMethod(listenerSetter, listenerType);//得到setOnClickListener的执行方法
                            setterMethod.invoke(view,listener);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

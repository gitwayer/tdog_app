package com.tdog.library;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

//代理拦截器
public class ListenerInvocationHandler implements InvocationHandler {
    private Object target;
    private HashMap<String,Method> map = new HashMap<>();

    public ListenerInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(target!=null) {
            String methodName = method.getName();//假如是onCLick方法
            Method proxyMethod = map.get(methodName);
            if (proxyMethod != null) {
                if(method.getGenericParameterTypes().length==0){//判断参数
                    return proxyMethod.invoke(target);
                }
                return proxyMethod.invoke(target,args);
            }
        }
        return null;
    }

    /**
     * 将待拦截的方法添加到集合中
     * @param methodName 需要拦截的方法名称（比如onClick）
     * @param method 执行自定义的方法（比如show）
     */
    public void addMethodMap(String methodName,Method method){
        map.put(methodName,method);
    }

}

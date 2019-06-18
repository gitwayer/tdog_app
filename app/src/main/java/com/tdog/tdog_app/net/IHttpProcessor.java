package com.tdog.tdog_app.net;

import java.util.Map;

/**
 * 处理post
 */
public interface IHttpProcessor {

    void post(String url, Map<String, Object> map, ICallback callback);
}

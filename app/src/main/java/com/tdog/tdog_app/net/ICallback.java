package com.tdog.tdog_app.net;

/**
 * 回调的callback
 */
interface ICallback {

    void onSuccess(String result);
    void onFailure();
}

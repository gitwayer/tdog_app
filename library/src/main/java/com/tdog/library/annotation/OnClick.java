package com.tdog.library.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnClickListener",listenerType = View.OnClickListener.class, callBack = "onClick")
public @interface OnClick {

    /**
     * setOnClickListener
     * View.OnClickListener
     * onClick
     */
    int[] value();
}

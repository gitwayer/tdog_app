package com.tdog.library.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnLongClickListener",listenerType = View.OnLongClickListener.class,callBack = "onLongClick")
public @interface OnLongClick {

    /**
     * setOnClickListener
     * View.OnClickListener
     * onClick
     */
    int[] value();
}

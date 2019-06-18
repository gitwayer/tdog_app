package com.tdog.library.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    /**
     * setOnClickListener
     * View.OnClickListener
     * onClick
     */
    String listenerSetter();
    Class<?> listenerType();
    String callBack();
}

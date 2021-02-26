package com.tamnn.vnexpressfeeds.dependency;

import android.content.Context;

public class ComponentUtil {

    public static <T> T getComponent(Context context, Class<T> clazz) {
        if (!(context instanceof HasComponent))
            throw new IllegalArgumentException(context + " must be instance of HasComponent.");

        Object obj = ((HasComponent) context).getComponent();

        if (!clazz.isAssignableFrom(obj.getClass()))
            throw new ClassCastException(obj.getClass() + " cannot be cast to " + clazz);

        return (T) obj;
    }
}
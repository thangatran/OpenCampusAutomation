package com.opencampus.automation.core;

import java.util.Map;

public class Global {
    private static final ThreadLocal<Map<String, Object>> threadLocalMap = new ThreadLocal<>();

    public static <T> void set(Class<T> clzz, T object) {
        threadLocalMap.get().put(clzz.getCanonicalName(), object);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> clzz) {
        return (T) threadLocalMap.get().get(clzz.getCanonicalName());
    }

    public static void setThreadLocalMap(Map<String, Object> map) {
        threadLocalMap.set(map);
    }
}

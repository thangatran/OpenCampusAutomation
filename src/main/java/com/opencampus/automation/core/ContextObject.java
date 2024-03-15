package com.opencampus.automation.core;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ContextObject<T> {
    private final Map<Class<T>, T> contextObjects;

    public ContextObject() {
        contextObjects = new HashMap<>();
    }

    public T getObjectInstance(Class<T> objectClassName) {
        T objectInstance;
        try {
            if (contextObjects.get(objectClassName) != null)
                objectInstance = contextObjects.get(objectClassName);
            else {
                objectInstance = objectClassName.getDeclaredConstructor().newInstance();
                contextObjects.put(objectClassName, objectInstance);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(String.format("Object: %s is not found | ERROR: %s ", objectClassName, e));
        }
        return objectInstance;
    }

    public T getObjectInstance(Class<T> objectClassName, Object constructorParameter) {
        T objectInstance;
        try {
            if (contextObjects.get(objectClassName) != null)
                objectInstance = contextObjects.get(objectClassName);
            else {
                objectInstance = objectClassName.getDeclaredConstructor(constructorParameter.getClass()).newInstance(constructorParameter);
                contextObjects.put(objectClassName, objectInstance);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(String.format("Object: %s is not found | ERROR: %s ", objectClassName, e));
        }
        return objectInstance;
    }
}

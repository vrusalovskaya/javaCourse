package org.example;

import org.example.annotations.NullableWarning;

import java.lang.reflect.Field;

public class AnnotationProcessor {

    public static void checkNullableWarning(Object object) {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NullableWarning.class)) {
                try {
                    if (field.get(object) == null) {
                        System.out.println("Variable [" + field.getName() + "] is null in [" + object.getClass() + "] !");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

package org.fpm.di.example;

import org.fpm.di.Binder;

import javax.inject.Singleton;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;

public class DummyBinder implements Binder {
    private final LinkedList<Class<?>> classes = new LinkedList<>();
    private final HashMap<Class<?>, Class<?>> classClassHashMap = new HashMap<>();
    private final HashMap<Class<?>, Object> classObjectHashMap = new HashMap<>();

    @Override
    public <T> void bind(Class<T> clazz) {
        if (!hasSingleton(clazz)) classes.add(clazz);
    }

    private Boolean hasSingleton(Class<?> clazz){
        if (clazz.isAnnotationPresent(Singleton.class)){
            try {
                classObjectHashMap.put(clazz, clazz.getConstructor().newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        classClassHashMap.put(clazz,implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        classObjectHashMap.put(clazz,instance);
    }

    public <T> Class<T> checkClass(Class<T> clazz){
        if(classes.contains(clazz)) return clazz;
        return null;
    }

    public <T> Class<T> checkImplementation(Class<T> clazz){
        if(classClassHashMap.containsKey(clazz)) return (Class<T>) classClassHashMap.get(clazz);
        return null;
    }

    public <T> T checkInstance(Class<T> clazz){
        if(classObjectHashMap.containsKey(clazz)) return (T) classObjectHashMap.get(clazz);
        return null;
    }
}

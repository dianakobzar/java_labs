package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DummyContainer implements Container {
    DummyBinder database;
    public DummyContainer(DummyBinder binder) {
        database = binder;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        if (database.checkClass(clazz)!=null)
            return makeInstance(clazz);
        if (database.checkInstance(clazz)!=null)
            return database.checkInstance(clazz);
        if (database.checkImplementation(clazz)!=null)
            return getComponent(database.checkImplementation(clazz));
        return null;
    }

    private <T> T makeInstance(Class<T> clazz) {
        if (checkInjection(clazz)!=null) return checkInjection(clazz);
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    private <T> T checkInjection(Class<T> clazz){
        for (Constructor<?> tConstructor: clazz.getConstructors())
            if (tConstructor.isAnnotationPresent(Inject.class)){
                try {
                    return (T) tConstructor.newInstance(getComponent(tConstructor.getParameterTypes()[0]));
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        return null;
    }
}

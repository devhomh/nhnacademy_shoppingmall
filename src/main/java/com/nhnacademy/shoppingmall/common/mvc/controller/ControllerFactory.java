package com.nhnacademy.shoppingmall.common.mvc.controller;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.exception.ControllerNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class ControllerFactory {
    public static final String CONTEXT_CONTROLLER_FACTORY_NAME="CONTEXT_CONTROLLER_FACTORY";
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();
    public void initialize(Set<Class<?>> c, ServletContext ctx){

        if(Objects.isNull(c)){
            log.info("Controller not found");
            return;
        }

        for (Class<?> clazz : c) {
            RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
            if(Objects.nonNull(mapping)){
                StringBuilder keyBuilder = new StringBuilder(mapping.method() + "-");
                Arrays.stream(mapping.value()).forEach(keyBuilder::append);
                try {
                    ctx.setAttribute(CONTEXT_CONTROLLER_FACTORY_NAME, this);
                    beanMap.put(keyBuilder.toString(), clazz.getDeclaredConstructor().newInstance());
                } catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
                    e.printStackTrace();
                }
            }
        }


    }

    private Object getBean(String key){
        if(beanMap.containsKey(key)){
            return beanMap.get(key);
        }
        throw new ControllerNotFoundException(key);
    }

    public Object getController(HttpServletRequest request){
        return getBean(getKey(request.getMethod(), request.getServletPath()));
    }

    public Object getController(String method, String path){
        if(method == null || path == null){
            throw new NullPointerException("Parameter value is null");
        }
        return getBean(getKey(method, path));
    }

    private String getKey(String method, String path){
        if(method == null || path == null){
            throw new NullPointerException("Parameter value is null");
        }
        return method + "-" + path;
    }
}

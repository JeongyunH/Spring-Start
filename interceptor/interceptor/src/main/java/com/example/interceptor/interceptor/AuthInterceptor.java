package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();

        log.info("request url : {}", url);

        boolean validAuthUser = checkValidAnnotation(handler, Auth.class);
        log.info("annotation check : {}", validAuthUser);

        if(validAuthUser){
            String query = uri.getQuery();
            if(query.equals("name=steve")){
                return true;
            }

            throw new AuthException();
        }

        return false;
    }

    private boolean checkValidAnnotation(Object handler, Class clazz){

        if(handler instanceof ResourceHttpRequestHandler){
            log.info("check class : {}", clazz);
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            log.info("atiwet : {}", clazz);
            return true;
        }

        return false;
    }
}

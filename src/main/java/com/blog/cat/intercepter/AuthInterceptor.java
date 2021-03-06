package com.blog.cat.intercepter;


import com.blog.cat.annotation.NormalToken;
import com.blog.cat.annotation.PassToken;
import com.blog.cat.dao.UserDao;
import com.blog.cat.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * @author admin
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //非映射方法直接通过
        if(! (handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //PassToken 注解的Controller方法直接放行
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }

        //NormalToken 注解的Controller 方法进行验证
        if (method.isAnnotationPresent(NormalToken.class)){
            NormalToken normalToken = method.getAnnotation(NormalToken.class);
            if (normalToken.required()){
                //处理token
                boolean handleFlag = tokenUtil.handlerToken(token,userDao);
                if(handleFlag){

                }
                return handleFlag;

            }
        }
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

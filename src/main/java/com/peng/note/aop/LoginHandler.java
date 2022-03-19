package com.peng.note.aop;


import com.peng.note.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author : code
 * @Date 2022/2/20 10:51
 * @Version 1.0
 */

public class LoginHandler implements HandlerInterceptor {


    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证权限
        if (this.hasPermission(request,handler)) {
            return true;
        }
        // 如果没有权限 则抛403异常 springboot会处理，跳转到 /error/403 页面
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "无权限");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 是否有权限
     *
     * @param handler
     * @return
     */
    private boolean hasPermission(HttpServletRequest request ,Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            UserLogin userLogin = handlerMethod.getMethod().getAnnotation(UserLogin.class);
            // 如果方法上的注解为空 则获取类的注解
            if (userLogin == null) {
                userLogin = handlerMethod.getMethod().getDeclaringClass().getAnnotation(UserLogin.class);
            }
            // 如果标记了注解，则判断权限
            if (userLogin != null) {
                String authorization = request.getHeader("Authorization");
               return tokenService.checkToken(authorization);
            }
        }
        return true;
    }
}

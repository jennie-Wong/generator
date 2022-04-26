package com.example.generator.interceptor;

import com.example.generator.anno.WriteOperLog;
import com.example.generator.logic.OperationLogLogic;
import com.example.generator.mapper.OperationLogMapper;
import com.example.generator.model.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author wangjing
 */
@Slf4j
@Component
public class WriteOperLogInterceptor implements HandlerInterceptor {

    @Autowired
    private OperationLogLogic operationLogLogic;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(o instanceof HandlerMethod)) {
            return true;
        }

        //方法注解级拦截器
        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要记录日志
        WriteOperLog logAnnotation = method.getAnnotation(WriteOperLog.class);

        if (logAnnotation != null) {
            //记录日志到数据库
            String url=httpServletRequest.getRequestURL().toString();
            String uri=httpServletRequest.getRequestURI().toString();
            OperationLog operationLog=new OperationLog();
            operationLog.setIp("要从前端穿的head获取");
            operationLog.setMethod(method.getName());
            operationLog.setMethodName(logAnnotation.description());
            operationLog.setUrl(url);

            operationLogLogic.insert(operationLog);
            log.info("日志已计入数据库");
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

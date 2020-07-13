package com.example.demo.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果
     * '@Pointcut("execution(* com.wwj.springboot.service.impl.*.*(..))")'
     */
    @Pointcut("@annotation(com.example.demo.aop.LogMethod)")
    public void operationLog() {
    }


    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        Date start = new Date();
        try {
            res = joinPoint.proceed();
            return res;
        } finally {
            try {
                //方法执行完成后增加日志
                addOperationLog(joinPoint, res, start);
            } catch (Exception e) {
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行后添加日志
     *
     * @param joinPoint
     * @param res
     * @param start
     */
    private void addOperationLog(JoinPoint joinPoint, Object res, Date start) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        Method method = signature.getMethod();
        LogMethod annotation = method.getAnnotation(LogMethod.class);

        String paramName = annotation.inputKey();
        Object input = null;
        if (!StringUtils.isEmpty(paramName)) {

            input = MethodKVUtil.getParamString(method, args, paramName);
        }
        LogDetail logDetail = new LogDetail();
        logDetail.setCallStartTime(start);
        logDetail.setCallEndTime(new Date());
        logDetail.setInput(objectMapper.writeValueAsString(input));
        logDetail.setOutput(objectMapper.writeValueAsString(res));

        System.out.println(objectMapper.writeValueAsString(logDetail));
    }


}

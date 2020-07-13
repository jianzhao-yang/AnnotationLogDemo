package com.example.demo.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodKVUtil {

    private static final ParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    /**
     * 核心方法,根据el表达式获取对应入参的json值,也可以修改为直接返回对象
     * 参数前面必须加#
     * @param method
     * @param parameterValues
     * @param paramName
     * @return
     */
    public static Object getParamString(Method method, Object[] parameterValues, String paramName) {
        EvaluationContext context = new MethodBasedEvaluationContext(null, method, parameterValues, NAME_DISCOVERER);
        Object value = PARSER.parseExpression(paramName).getValue(context);
        return value;
    }
}

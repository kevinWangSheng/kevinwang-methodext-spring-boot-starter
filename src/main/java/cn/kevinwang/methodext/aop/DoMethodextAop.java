package cn.kevinwang.methodext.aop;

import cn.hutool.json.JSONUtil;
import cn.kevinwang.methodext.annotation.DoMethodext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wang
 * @create 2024-01-18-18:58
 */
@Aspect
@Component
public class DoMethodextAop {
    @Pointcut("@annotation(cn.kevinwang.methodext.annotation.DoMethodext)")
    public void poincut(){}

    @Around("poincut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        Method method = getMethod(jp);
        DoMethodext doMethodext = method.getAnnotation(DoMethodext.class);
        Method methodext = jp.getTarget().getClass().getMethod(doMethodext.method(), method.getParameterTypes());
        Class<?> insertMethodReturnType = methodext.getReturnType();
        if(!insertMethodReturnType.getName().equals("boolean")){
            throw new RuntimeException("annotation @DoMethodExt set method：" + methodext.getName() + " returnType is not boolean");
        }

        boolean invoke = (boolean) methodext.invoke(jp.getThis(), jp.getArgs());
        return invoke ? jp.proceed() : JSONUtil.toBean(doMethodext.returnJson(), method.getReturnType());
    }

    public Method getMethod(ProceedingJoinPoint jp) throws NoSuchMethodException {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(),methodSignature.getParameterTypes());
    }
}

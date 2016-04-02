package com.mkyong.common.controller.aspect;

import com.google.gson.Gson;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User:jiandan
 * Date:2015/6/5.
 * Time:14:34.
 * INFO:定义环绕通知切面
 */
@Component
@Aspect
public class MethodAspect {

    private static final Logger logger = LoggerFactory.getLogger(MethodAspect.class);
    
    public static final String EDP = "execution(* com.mkyong.common.controller..*.*(..))";

//    @Before(EDP)    //spring中Before通知
//    public void logBefore() {
//        System.out.println("logBefore:现在时间是:"+new Date());
//    }
//
//    @After(EDP)    //spring中After通知
//    public void logAfter() {
//        System.out.println("logAfter:现在时间是:"+new Date());
//    }
//
//
//    @Around(EDP)
    public Object validator(ProceedingJoinPoint joinPoint) throws Throwable {
        Gson gson=new Gson();

        String cName = joinPoint.getTarget().getClass().getName();
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Object[]args=getArgs(joinPoint);

        long sTime=System.nanoTime();
     //   logger.error(cName + "." + method.getName() + "  " + gson.toJson(args));
        System.out.println("logAround开始:现在时间是:"+new Date());
        Object result=  joinPoint.proceed(args); //调用目标方法
        System.out.println("logAround结束:现在时间是:"+new Date());
        long eTime=System.nanoTime();

        StringBuffer buffer=new StringBuffer();// 这里要不要用 StringBuffer，但其效率高
        buffer.append(cName).
                append(".").
                append(method.getName()).
                append("  ").
                append(gson.toJson(args)).
                append("  result ").
                append(gson.toJson(result)).
                append("  consumeTime: ").
                append((eTime-sTime)/1000000).
                append(" ms");


        logger.info(buffer.toString());
        System.out.println(buffer.append("监控信息").toString());

        return result;
    }


    public Object[] getArgs(ProceedingJoinPoint joinPoint){
        Object[] arguments = joinPoint.getArgs();
        return arguments;
    }


}

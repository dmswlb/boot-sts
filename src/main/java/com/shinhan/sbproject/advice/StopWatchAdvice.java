package com.shinhan.sbproject.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//보조업무_수행시간 계산
@Component
@Aspect
@Order(1)	//낮은 것이 먼저
public class StopWatchAdvice{
	@Pointcut("execution(* f1(..))")
	public void mypontcut1() {
		//로직없음, 함수 형태일 때만 가능한 annotation 사용
	}
	@Pointcut("execution(* select*())")
	public void mypontcut2() {
		//로직없음, 함수 형태일 때만 가능한 annotation 사용
	}
	
	@Around("mypontcut1()")	
	public Object invoke(ProceedingJoinPoint jp) throws Throwable {
		String targetMethodName = jp.getSignature().toShortString();
		
		//보조업무
		System.out.println("******" + targetMethodName + "메서드 호출 전");
		StopWatch watch = new StopWatch("계산시간");
		watch.start();
		//주업무를 수행한다. 
		Object object = jp.proceed();
		//보조업무
		System.out.println("*****" + targetMethodName + "메서드 호출 후");
		watch.stop();
		System.out.println("주업무 수행 시간:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		return object;
	}
}

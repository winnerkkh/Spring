package com.javalec.ex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	
	//핵심코드의 어느 범위, 어느 메소드까지 공통기능을 적용하겟다
	@Pointcut("within(com.javalec.ex.*)")
	private void pointcutMethod() { //@Pointcut Annotation 을 가진 메소드 하나를 만들어야함
	}

	@Around("pointcutMethod()") //@Around가 적용되는 포인트컷은 위쪽에 정해준 포인트컷 메소드
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start.");
		long st = System.currentTimeMillis();

		try {
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 경과시간 : " + (et - st));
		}

	}

	//@Pointcut을 이용하지 않고 바로  advice값으로 아래와 같이 적용활수 있다
	//핵심코드가 실행되기 바로전에 실행된다.

	@Before("within(com.javalec.ex.*)")
	public void beforAdvice() {
		System.out.println("beforAdvice()");
	}

}

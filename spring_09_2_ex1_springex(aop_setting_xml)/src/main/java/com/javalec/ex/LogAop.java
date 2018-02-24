package com.javalec.ex;

import org.aspectj.lang.ProceedingJoinPoint;

//대행자 proxy
// 공통 기능

public class LogAop {
	
	//실제 기능을 할 메소드 => 핵심기능이 여러게 있을수 있기에 그 핵심기능을 ProceedingJoinPoint통하여 가져오기 
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		
		//메소드만 aop의 적용되기에 어떤 메소드인지  이름 출력
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println( signatureStr + " is start.");
		
		long st = System.currentTimeMillis(); //공통 기능(시간 확인하는것)
		
		try {
			Object obj = joinpoint.proceed(); //joinpoint는 핵심기능, proceed()는 핵심기능을 실행시켜주는 메소드
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println( signatureStr + " is finished.");
			System.out.println( signatureStr + " 경과시간 : " + (et - st));
		}
		
	}
	
}

package com.iu.trans;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//Advice(공통적인 관심사)
@Component
@Aspect
public class CardCheck {
	@Around("execution(* com.iu.trans.Transport.*())")
	public Object check(ProceedingJoinPoint join) {
		System.out.println("카드찍기");
		System.out.println("학생입니다.");
		Object obj = null;
		try {
			obj = join.proceed();	//bus()
		} catch (Throwable e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
		
		System.out.println("카드찍기");
		System.out.println("삑--");
		return obj;
	}
	@Before("execution(* com.iu.trans.Trip.*())")
	public void packing() {
		System.out.println("짐 싸기");
	}

}

package com.iu.trans;

import org.springframework.stereotype.Component;

//target
@Component
public class Trip {

	//pointcut
	public void go() {
		System.out.println("=====================");
		System.out.println("여행 가기");
		System.out.println("=====================");
	}
}

package com.example.demo.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAOP {
	/* 1. 如果發生Exception:  Around -> Before -> Controller -> After returning -> After -> Around
	   2. 如果沒發生Exception: Around -> Before -> Controller -> After throwing -> After
	*/
	
	@Autowired
	private HttpServletRequest request;
	
	@Pointcut("execution(* com.example.demo.controller.MyController..*(..))")
	public void myPointcut() {
		
	}
	
	@Before("myPointcut()")
	public void beforePointcut() {
		System.out.println("Before");
	}
	
	// 即使方法發生例外，也會執行
	@After("myPointcut()")
	public void afterPointcut() {
		System.out.println("After");
	}
	
	@Around("myPointcut()")
	public void aroundPointcut(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Around start");
//		try {
			pjp.proceed();
//		} catch(Exception e) {
//			System.out.println("Exception catched");
//		}
		System.out.println("Around finish");
	}

	@AfterThrowing(pointcut = "myPointcut()", throwing = "error") 
	public void afterThrowingPointCut(Throwable error) {
		System.out.println("After throwing");
		System.out.println("Exception URL: " + request.getRequestURL());
		System.out.println("Exception is: " + error.getMessage());
	}
	
	// 方法發生例外，不會執行
	@AfterReturning("myPointcut()")
	public void afterReturningPointcut() {
		System.out.println("After returing");
	}
}

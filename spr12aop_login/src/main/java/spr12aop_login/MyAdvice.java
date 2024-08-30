package spr12aop_login;

import java.util.Scanner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {
	
	@Around("execution(public void startProcess())") 
	//main 파일의 startProcess()를 프록시 객체가 가로채서 위의 startProcess를 실행하도록 한다.
	//위의 public void startProcess()는 아래 joinpoint.proceed()에서 수행된다.
	public Object abc(ProceedingJoinPoint joinPoint) throws Throwable{
		
		System.out.println("AOP 시작: 핵심 로직 수행 전 id 검증");
		
		System.out.println("input id:");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine();
		scanner.close();
		
		if(!id.equals("ok")) {
			System.out.println("id 불일치! 작업을 종료합니다");
			return null;
		}
		
		Object object = joinPoint.proceed();
		
		return object;
	}
}

package pack;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//aopinit.xml에서 autoProxy를 해줬기 때문에 아래 @aspect가 걸린 클래스는 
//LogicImpl의 타겟 빈 @service를 자동으로 advice한다.(@Around 적용)
@Aspect  // aop의 관심사항을 갖는 클래스, 별도 클래스로 만들수도 있고 이렇게 선언해줘도 됨
@Component 
public class OurAdvice { //Aspect 클래스 : Advice용
	@Around("execution(* *..*LogicInter*.*(..)) or execution(public void selectAll())")
	public Object kbs(ProceedingJoinPoint joinPoint) throws Throwable{
		// 수행 시간 체크
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		System.out.println("핵심 메소드 수행 전 관심사항 실행");
		
		Object object = joinPoint.proceed(); // 선택된 핵심 메소드 수행
		
		System.out.println("핵심 메소드 수행 후 뭔가를 실행");
		
		stopWatch.stop();
		System.out.println("처리 시간 : " + stopWatch.getTotalTimeSeconds());
		return object;
	
	};
		
}

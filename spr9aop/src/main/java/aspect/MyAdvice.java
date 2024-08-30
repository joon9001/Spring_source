package aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// 핵심 로직에 삽입할 관심 코드 : 예 - transaction, login, security, log ... 
//MethodInterceptor는 원래 메소드(핵심 로직)를 가로채어 앞,뒤로 추가 비즈니스 로직을 구현한다.
public class MyAdvice implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// joinpoint에 삽입되어 동작할 코드 기술
		//MethodInvocation invocation: 가로챈 메소드 호출을 캡슐화하는 객체,
		//이 객체는 메소드에 대한 정보와 원래의 메소드를 호출할 수 있는 기능을 제공함
		System.out.println("핵심 로직 수행 전 뭔가를 처리");
		// target 메소드 이름 얻기 
		String tmname = invocation.getMethod().getName();
		//invocation.getMethod().getName(): 원래 실행되어야할 메소드의 이름을 얻습니다.
		System.out.println("적용된 메소드명 : " + tmname);
				
		Object object = invocation.proceed(); 
		//원래의 메소드를 호출합니다. 
		//이 메소드 호출을 통해 원래의 비즈니스 로직이 실행됩니다.
		// 선택된 핵심 로직 메소드 중 하나 = sayHi()
		
		System.out.println("핵심 로직 수행 후 마무리 처리");
		
		return object;
	}
}

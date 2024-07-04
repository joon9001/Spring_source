package pack.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		/*
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:arrange.xml");
		*/
		
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("classpath:arrange.xml");
		System.out.println("---singleton 확인하세요");
		MessageImpl impl1 = (MessageImpl)context.getBean("mImpl");
		impl1.sayHi();
		MessageImpl impl2 = (MessageImpl)context.getBean("mImpl");
		impl2.sayHi();
		System.out.println("객체 주소 : " + impl1 + " " + impl2);
		
		System.out.println("\n---다형성 처리하세요");
		MessageInter inter = (MessageInter)context.getBean("mImpl");
		//다형성: 클래스가 아닌 인터페이스로 받기 
		inter.sayHi();
		
		System.out.println("\n---다형성 처리하세요2");
		MessageInter inter2 = context.getBean("mImpl", MessageInter.class);
		//MessageInter.class를 ()안에 써주면 위에처럼 casting을 안해줘도 된다.
		inter2.sayHi();
		
		context.close();
	}
}

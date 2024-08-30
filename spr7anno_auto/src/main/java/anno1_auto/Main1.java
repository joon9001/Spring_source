package anno1_auto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//@Configuration //XML 대신에 클래스로 환경을 잡을때(bean 객체 생성) 사용한다. 
//@ComponentScan(basePackages = "anno1_auto")
public class Main1 {

	public static void main(String[] args) {
		// @AutoWired에 대한 메인
		//AnnotationConfigApplicationContext context = 
		//		new AnnotationConfigApplicationContext(Main1.class); //클래스명으로 XML 환경을 대체하겠다는 의미
		//configuration을 안쓰고 XML을 쓸 경우 위의 방법 대신 아래 방식을 쓰면 된다. 
		ApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:anno1.xml");
		
		SenderProcess process = 
				context.getBean("senderProcess", SenderProcess.class);
		process.dispData();
	
	}

}

package anno1_auto;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "anno1_auto")
public class Main1 {

	public static void main(String[] args) {
		// @AutoWired에 대한 메인
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(Main1.class);
		SenderProcess process = 
				context.getBean("senderProcess", SenderProcess.class);
		process.dispData();
	}

}

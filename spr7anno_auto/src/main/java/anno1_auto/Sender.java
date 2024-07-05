package anno1_auto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component // 싱글톤으로 sender 객체 생성, 객체 변수명은 sender
//@Component("sender") //위와 같은 의미 
//@Component("sen") 
//@Scope("singleton") 

public class Sender implements SenderInter{
	public void show() {
		System.out.println("Sender의 show method 수행");
	}
}

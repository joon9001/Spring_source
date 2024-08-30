package anno3_etc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("my")
public class MyProcess {
	
	//@Value("#{dataInfo.name}") 	
	// @Value변수에 값을 초기화하기 위해 사용할 수도 있다.
	// Spring EL : #{표현식}, 만들어진 Component 객체를 이용,private는 getter를 이용해서 가져옴

	@Value("#{dataInfo.name}") //객체.멤버로 값 주기 
	private String name;
	
	private String part;
	
	@Autowired
	//public MyProcess(@Value("영업부") String part) {
	public MyProcess(@Value("#{dataInfo.part}") String part) {
		this.part = part;
	}
	@Value("123") // value 어노테이션으로 값을 줄 때 기본이 String 타입이다, (int형태로 주면 에러)
	//String으로 123을 줘도 int값으로 자동 형변환되어 age에 저장된다. 
	private int age;
	
	@Value("1,2,3,4")
	private int arr[];
	
	public void showData() {
		System.out.println("name : " + name);
		System.out.println("part : " + part);
		System.out.println("age : " + age);
		System.out.println(arr[0] + " " + arr[3]);
	}
}

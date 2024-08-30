package anno3_etc;

import org.springframework.stereotype.Component;

@Component  //component에 ="" 값을 안 줄 경우 객체변수명은 dataInfo가 된다.
public class DataInfo {
	private String name = "가나다";
	private String part = "총무부";
	
	public String job = "프로그래머";
	
	public String getName() {
		return name;
	}
	
	public String getPart() {
		return part;
	}
}

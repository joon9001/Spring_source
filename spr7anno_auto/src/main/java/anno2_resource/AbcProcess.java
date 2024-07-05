package anno2_resource;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class AbcProcess {
	@Resource(name="abc1") // type에 의한 매핑이 아니라 이름에 의한 매핑
	private Abc1 abc1;
	
	private Abc2 abc2;
	
	@Resource(name="aaa") // Abc2 클래스의 객체변수명이 aaa로 component 설정을 했기 때문에 aaa로 받아와야 한다.
	public void setAbc2(Abc2 abc2) {
		this.abc2 = abc2;
	}
	public void showData() {
		abc1.setIrum("금요일");
		abc2.setNai(23);
		
		String str = "결과 : 이름은 " + abc1.getIrum();
		str += ", 나이는 " + abc2.getNai();
		System.out.println(str	);
	}
}

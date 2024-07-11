
package pack;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BusinessImpl implements BusinessInter {
	//모델 클래스를 사용
	@Autowired
	@Qualifier("sangpumImpl") // SangpumInter의 파생 클래스가 여러개일 경우 에러가 일어나므로 특정 클래스명을 지정해준다.
	private SangpumInter inter;
	@Override
	public void selectProcess() {
		ArrayList<SangpumDto> myList = inter.selectAll();
		
		for(SangpumDto s:myList) {
			System.out.println(s.getCode() + " " +
					s.getSang() + " " +
					s.getSu() + " " +
					s.getDan());
		}
	}
}

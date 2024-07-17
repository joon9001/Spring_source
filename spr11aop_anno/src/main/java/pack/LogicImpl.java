package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //business 로직을 처리하는 서비스
//핵심 비즈니스 로직을 의미하며 advice(before,after,around)의 타겟 빈이다.
public class LogicImpl implements LogicInter{
	
	@Autowired // autowired를 선언했으므로 빈 생성은 따로 안해도 됨
	private ArticleInter articleInter;

		public LogicImpl() {
			// TODO Auto-generated constructor stub
		}
		

		@Override
		public void selectDataProcess1() {
			System.out.println("selectDataProcess1 수행");
			articleInter.selectAll(); // 모델 클래스 수행 결과
			
			System.out.println("----------------");
		}
		
		@Override
		public void selectDataProcess2() {
			System.out.println("selectDataProcess2 처리");
			articleInter.selectAll(); // 모델 클래스 수행 결과
			
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
}

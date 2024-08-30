package pack;

import org.springframework.stereotype.Repository;

@Repository //DB 연결 작업을 하는 Model 영역의 클래스라는 것을 선언, component에 소속됨
public class ArticleDao implements ArticleInter{
	@Override
	public void selectAll() {
		System.out.println("테이블 자료 읽기");
		
	}
}

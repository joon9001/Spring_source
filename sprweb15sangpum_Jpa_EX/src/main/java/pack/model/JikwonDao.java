package pack.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JikwonDao {
	@Autowired
	private jikRepo repo;
	
	//특정 자료 읽기 (spring data JPA의 자동생성 함수(JPA Query Method)인 findByJik을 이용하여
	//내부적으로 SQL 문을 생성하여 자료를 검색한다. findby + 변수명(여기서는 Jik)으로 jik 속성을 이용하여 검색한다.
	public List<JikEntity> getSearchValue(String jik){
		List<JikEntity> list = repo.findByJik(jik);
		return list;
	}
}

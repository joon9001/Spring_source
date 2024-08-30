package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SangpumRepository extends JpaRepository<Sangpum, Integer>{
	// 검색
	// 메소드 네이밍룰 : findBy[엔티티명]칼럼명..., readBy[엔티티명]칼럼명..., getBy[엔티티명]칼럼명...
	// findBy변수명And변수명, findBy변수명Or변수명, findBy변수명 GreaterThanEqual... 등,
	// 위에 <>는 Sangpum 엔티티를 대상으로 하고, 그 기본 키 타입이 Integer임을 나타냅니다.
	
	List<Sangpum> findBySangContaining(String svalue); // 검색어가 포함된 : like '%검색어%'
	//네이밍 규칙을 따르는 메서드로, 이를 호출할 때는 별도의 JPQL 쿼리를 작성하지 않고도 
	//Spring Data JPA가 내부적으로 쿼리를 생성하여 실행
	List<Sangpum> findBySangStartingWith(String svalue); // 검색어로 시작되는 : like '검색어%'

	List<Sangpum> findBySangEndingWith(String svalue); // 검색어로 끝나는 : like '%검색어'
	
	// JPQL 사용
	@Query(value = "select s from Sangpum as s where s.sang like %:svalue%")
	List<Sangpum> searchLike(@Param("svalue") String svalue);
}

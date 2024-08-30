package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductCrudRepository extends JpaRepository<ProductVo, Integer> {
	//CrudRepository > PagingAndSortingRepository > JpaRepository
	//위는 계급관계, JpaRepository는 CrudRepository의 기능과 pagingAndSorting 기능까지 다 가졌다.
	
	//메소드 이름으로 쿼리 생성 방법 find + (엔터티 이름) + By + 변수 이름
	// 엔티티의 이름은 생략이 가능
	//참고 링크: https://kihwan95.tistory.com/5
	ProductVo findByCode(Integer code);
	
	//JPQL (참고 링크: https://cafe.daum.net/flowlife/HrhB/79)
	@Query(value = "select p from ProductVo as p")
	List<ProductVo> findAllData();
	
	//JPQL의 장점: DB 종류가 바뀌어도 SQL 코드를 수정하지 않아도 됨
	// where 조건 
	//이름 기반
	@Query(value = "select p from ProductVo as p where p.code=:code")
	ProductVo findByCodeMy(@Param("code") int code);
	// 순서 기반
	@Query(value = "select p from ProductVo as p where p.code=?1")
	ProductVo findByCodeMy2(int code);
	// 순서 기반 (복수개)
	@Query(value = "select p from ProductVo as p where p.code=?1 or p.sang=?2")
	List<ProductVo> findByData(int code, String sang);
	//복수개의 칼럼이 들어오므로 List 타입
	
	// native Query문 사용 (위의 jpql과 다르게 실제 sql문 사용, nativeQuery의 기본값 false를 true로 바꿔줘야함)
	@Query(value = "select code,sang,su,dan from product where code <= 5", nativeQuery = true)
	List<ProductVo> findAllData2();
}

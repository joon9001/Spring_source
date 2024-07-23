package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JikwonRepository extends JpaRepository<Jikwon, Integer>{
	//Spring Data JPA는 메소드 이름을 분석하여 동적으로 SQL 쿼리를 생성합니다.
	List<Jikwon> findByBuserNum(int buserNum);
	//findByBuserNum 메소드는 내부적으로 SELECT * FROM Jikwon WHERE buserNum = ? 쿼리를 생성합니다.
	List<Jikwon> findByBuserNumAndJikwonRating(int buserNum, String rating);
//findByBuserNumAndJikwonRating 메소드는 내부적으로 SELECT * FROM Jikwon WHERE buserNum = ? AND jikwonRating = ? 쿼리를 생성합니다.
}
/*
예: jikwonRepository.findByBuserNum(1);
이 호출은 buserNum이 1인 Jikwon 엔티티의 리스트를 반환합니다.
*/
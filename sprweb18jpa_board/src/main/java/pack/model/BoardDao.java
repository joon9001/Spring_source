package pack.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import pack.controller.BoardBean;

@Repository
public class BoardDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DataRepository dataRepository;

	public List<Board> list() { // 전체 자료 읽기
		List<Board> list = dataRepository.findAll();
		logger.info("list size : " + list.size());

		return list;

	}

	public List<Board> search(BoardBean bean) { // 전체 자료 읽기
		List<Board> slist = null;

		if (bean.getSearchName().equals("author")) {
			slist = dataRepository.searchLike(bean.getSearchValue());
		} else {
			slist = dataRepository.searchLike2(bean.getSearchValue());
		}

		return slist;

	}

	@Transactional // 프록시 객체는 해당 메소드가 처리될 때 commit or rollback 알아서 수행
	// CheckedException 또는 예외가 없는 경우 commit 수행
	// uncheckedException가 발생하면 rollback 수행
	public String insertData(BoardBean bean) {

		try {
			// 새글 입력 시 가장 큰번호를 얻어 +1
			int max = dataRepository.maxNum();

			Board dto = new Board();
			dto.setNum(max + 1);
			dto.setAuthor(bean.getAuthor());
			dto.setTitle(bean.getTitle());
			dto.setContent(bean.getContent());
			dto.setBwrite(Timestamp.valueOf(LocalDateTime.now()));
			dto.setReadcnt(0);

			dataRepository.save(dto);

			return "success";
		} catch (Exception e) {
			return "입력오류 : " + e.getMessage();
		}
	}

	@Transactional
	public Board detail(int num) {
		// 조회수 증가
		dataRepository.updateReadcnt(num);
		// Spring Data JPA를 사용할 때 Repository에서 findById()의 반환값은 Optional 타입입니다.
		// JAVA의 영원한 숙적인 NullPointerException을 방지해주는.
		// 즉, null인 값을 참조해도 NullPointerException이 발생하지 않도록 값을 래퍼로 감싸주는 타입입니다.
		Optional<Board> board = dataRepository.findById(num);
		logger.info("board :: {}", board.get());

		if (board.isPresent()) {
			return board.get(); 
		// board는 optional 타입이므로 객체를 넘기려면 board.get()으로 return해야한다.
		} else {
			return new Board();
		}
	}
	
	@Transactional
	public String updateData(BoardBean bean) {
		try {
			
			// 조회수도 수정에 참여하기 위한 선행 작업
			Optional<Board> board = dataRepository.findById(bean.getNum());
			Board imsi = board.get();
			
			/*
			Board dto = new Board();
			dto.setNum(bean.getNum());  //이미 등록된 num이므로 수정
			dto.setAuthor(bean.getAuthor());
			dto.setTitle(bean.getTitle());
			dto.setContent(bean.getContent());
			dto.setReadcnt(imsi.getReadcnt());
			
			dataRepository.save(dto);
			*/
			//위에처럼 save 없이 아래처럼 써도 가능
			imsi.setAuthor(bean.getAuthor());
			imsi.setTitle(bean.getTitle());
			imsi.setContent(bean.getContent());
			imsi.setReadcnt(imsi.getReadcnt());
			
			return "success";
		} catch (Exception e) {
			return "수정오류 : " + e.getMessage();
		}
	}
	@Transactional
	public String deleteData(int num) {
		try {
			dataRepository.deleteById(num);
			
			
			
			return "success";
		} catch (Exception e) {
			return "삭제 오류 : " + e.getMessage();
		}
	}
	
}

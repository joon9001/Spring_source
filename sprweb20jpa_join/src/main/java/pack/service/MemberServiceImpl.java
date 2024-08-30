package pack.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.transaction.Transactional;
import pack.dto.MemberDto;
import pack.entity.Member;
import pack.repository.MemberRepository;
//@Service 어노테이션은 비즈니스 로직을 처리하는 서비스 클래스를 나타냅니다. 
//주로 비즈니스 로직을 캡슐화하여 컨트롤러와 데이터 접근 계층(Repository) 사이에서 역할을 합니다.
//컨트롤러는 주로 HTTP 요청과 응답을 처리하고, 서비스는 비즈니스 로직을 담당하며, 리포지토리는 데이터베이스 접근을 담당합니다. 
//이렇게 컨트롤러와 서비스의 역할을 분리하면 코드의 가독성과 유지보수성이 향상됩니다.
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberRepository memRepository;
	
	@Override
	public void getList(Model model) {
		/*
		// 방법 1: Member 전체 자료 반환 : 기본 메소드 사용
		// Member 엔티티를 MemberDto 객체로 전달
		
		List<Member> entityList = memRepository.findAll();
		
		List<MemberDto> list = new ArrayList<MemberDto>();
		for(Member temp:entityList) {
			MemberDto dto = new MemberDto();
			dto.setNum(temp.getNum());
			dto.setName(temp.getName());
			dto.setAddr(temp.getAddr());
			list.add(dto);
		}
		*/
		
		//방법 2: List<Member>를 Stream으로 변경해서 map()을 사용 List<MemberDto>로 변경하기
		List<MemberDto> list = memRepository
								.findAllByOrderByNumDesc()
								.stream()
								.map(item -> MemberDto.toDto(item)).toList();
		
		
		/*
		//방법 3: 위의 람다 표현식을 아래 메소드 참조 표현식으로 기술한 것(클래스명::메소드명)
		List<MemberDto> list2 = memRepository
								.findAllByOrderByNumDesc()
								.stream()
								.map(MemberDto::toDto).toList();
		
		*/
		/*
		stream()은 컬렉션(List,set)을 스트림으로 변환합니다. 
		이제 List<MemberDto>는 스트림으로 처리됩니다.
		이 스트림은 다양한 중간 연산(필터링, 매핑 등)과 최종 연산(반복, 수집 등)을 수행할 수 있습니다.
		
		map() 메서드는 스트림의 각 요소에 대해 주어진 함수를 적용하여 새로운 요소로 변환합니다. 
		원본 스트림의 요소를 다른 타입의 요소로 매핑할 때 주로 사용됩니다.
		여기서는 Member entity 타입을 MemberDto 타입으로 바꿔주는 역할을 한다.
		
		toList()는 최종적으로 변환된 MemberDto 요소들을 리스트로 수집합니다. 
		이 메서드는 스트림의 최종 연산으로, 변환된 스트림 요소들을 새로운 List<MemberDto>로 수집합니다.
		
		MemberDto 클래스의 객체 메소드 toDto를 메서드 레퍼런스로 표현하면 위와 같이 MemberDto::toDto가 된다.
		*/
		model.addAttribute("list", list); // 컨트롤러에 MemberDto가 담긴 list를 전달
	}
	
	
	@Override
	public void insert(MemberDto dto) {
		//JPA 작업 영역 내로 들어갈 때 일반 자료 전달용 객체 (Dto, FormBean)를 대응하는 엔티티로 변환해줌
		memRepository.save(Member.toEntity(dto));
		
	}
	//수정 자료 읽기
	@Override
	//model은 void라 return 값이 없어도 데이터 반환을 자동으로 한다.
	public void getData(Long num, Model model) {
		Member m = memRepository.findById(num).get();
		//findById는 기본키를 기준으로 검색하므로 기본 키로 설정된 Member의 num을 기준으로 검색한다
		model.addAttribute("dto", MemberDto.toDto(m));
	}
	@Override
	public void update(MemberDto dto) {
		memRepository.save(Member.toEntity(dto));
  //사용자가 입력한 데이터를 dto 객체에 담아 entity로 변환 후 memRepository에 save하면 DB의 데이터가 업데이트된다.
		//위에는 1차 캐시를 안씀 -> 동일한 요청에도 계속 DB 조회
		//setter 역할을 대신하는 save
	}
	@Transactional //1차 캐시 사용, commit, rollback 가능
	@Override
	public void update2(MemberDto dto) {
		 // 수정할 회원의 번호를 이용해서 회원 정보 entity 객체 얻어내기
	    Member m1 = memRepository.findById(dto.getNum()).get();
	    Member m2 = memRepository.findById(dto.getNum()).get();
	    
	    // 동일성 검사
	    boolean isEqual = m1 == m2;
	    System.out.println("m1과 m2가 같냐?" + isEqual);
	    
	    // setter 메소드를 이용해서 이름과 주소 수정하기
	    m1.setName(dto.getName());
	    m1.setAddr(dto.getAddr());
		
	}
	@Override
	public void delete(Long num) {
		memRepository.deleteById(num);
	}
}

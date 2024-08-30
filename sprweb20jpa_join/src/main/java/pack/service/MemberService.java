package pack.service;

import org.springframework.ui.Model;

import pack.dto.MemberDto;

public interface MemberService {
	public void getList(Model model);
	public void insert(MemberDto dto);
	public void getData(Long num, Model model);
	public void update(MemberDto dto);
	public void update2(MemberDto dto);
	public void delete(Long num);
}
//Model 객체는 Controller에서 View로 데이터를 전달하기 위해 사용됩니다.
//getList와 getData는 컨트롤러가 DB의 데이터를 모델에 담아 뷰에 전달하여야 하므로 Model model이 인수에 필요하다.
//반면, insert,update,updat2,delete는 DB에 삽입,수정,삭제 작업만 하면 되고 뷰에 데이터를 전달할 필요가 없으므로
//Model model을 인수에 넣어줄 필요가 없는것이다.

//controller - service - jpa - db 순으로 서버 데이터에 접근 후
//다시 db - jpa - service - controller 순으로 서버에서 데이터를 가져와 클라이언트 사이드로 출력한다.
//엔터티와 dto를 분리함으로써 보안 향상 및 유지 보수의 효율성을 높이는 목적이 있다.
// 이때, 서비스에서 중간 역할을 하면서 Entity를 DTO로 변환하여 컨트롤러에 반환하거나, 
// 컨트롤러에서 받은 DTO를 Entity로 변환하여 리포지토리에 저장한다.
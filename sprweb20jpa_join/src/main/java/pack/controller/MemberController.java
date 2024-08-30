package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.dto.MemberDto;
import pack.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mservice;
	
	@GetMapping("/member/mlist")
	public String memlist(Model model) {
		mservice.getList(model); //이렇게만 써도 mlist라는 키에 값이 담긴다.
		//MemberServiceImpl 인터페이스의 getlist method가 void 타입이라 return 값이 없음에도 불구하고
		//위의 getList(model)로 값을 불러올 수 있는 것은 model 객체는 스프링이 제공하는 모델을 사용하는 것이므로 
		//따로 반환을 해주지 않아도 되기 때문이다.
		
		//model.addAttribute("mlist", model);
		return "member/mlist";
	}
	//회원 추가
	@GetMapping("/member/insertform")
	public String insertform() {
		return "member/insertform";
	}
	
	@PostMapping("/member/insert")
	public String insert(MemberDto fbean) {
		mservice.insert(fbean);
		
		return "member/insert";
	}
	
	//회원 수정
	@GetMapping("/member/updateform")
	public String updateform(@RequestParam("num") Long num, Model model) {
		mservice.getData(num, model);
		//MemberserviceImpl에서 model.addAttribute("dto", MemberDto.toDto(m))으로 
		//기존의 데이터를 읽어와 dto에 저장한 후 updateform으로 이동한다.(updateform에서 value 값으로 dto 사용)
		return "member/updateform";
	}
	
	@PostMapping("/member/update")
	public String update(MemberDto fbean) {
		//mservice.update(fbean);
		mservice.update2(fbean);
		return "member/update";
	}
	//회원 삭제
	@GetMapping("/member/delete")
	public String delete(@RequestParam("num") Long num) {
		mservice.delete(num);
		return "redirect:/member/mlist";
	}
}

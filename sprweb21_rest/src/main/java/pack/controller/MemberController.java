package pack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pack.dto.MemberDto;
import pack.repository.MemberDao;

//@Controller
//@ResponseBody // 응답시 JSON으로 응답
//위의 2줄은 아래 @RestController로 표현 가능하다.
@RestController  //비동기처리에서 사용, 객체 데이터를 JSON 형태로 변환해 반환하는 역할
public class MemberController {
	@Autowired
	private MemberDao dao;
	
	/*
//	@GetMapping("/members")
//	public String list(Model model) {
//		List<MemberDto> list = dao.getList();
//		model.addAttribute("list", list);
//		return "list";
//	}
	
	@GetMapping("/members")
	public MemberDto list(Model model) {
		MemberDto dto = new MemberDto();
		dto.setNum(1);
		dto.setName("공기밥");
		dto.setAddr("강남구 역삼동");
		return dto;
	}
	
	@GetMapping("/insertform")
	public String insertform() {
		return "insertform";
	}
	
	@PostMapping("/insert")
	public String insert(@RequestParam("name")String name,
			@RequestParam("addr")String addr) {
		MemberDto dto = new MemberDto();
		dto.setName(name);
		dto.setAddr(addr);
		dao.insert(dto);
		
		return "redirect:/members"; //추가 후 목록보기
	}
	*/
	
	//---------------------REST 요청 처리---------------------------------------------------
	@GetMapping("/members")
	public List<MemberDto> getList(){  
		// (위의 @RestController 덕분에) DB자료를 읽어 HTML 파일로 반환하는게 아니라 
		// JSON 형태로 변환해 클라이언트(Javascript Ajax요청)에게 반환한다.
		System.out.println("get 요청했네~");
		return dao.getList();  
	}
	//POST를 써줬기 떄문에 값을 담아서 요청을 해온다.
	@PostMapping("/members") //@PostMapping: create
	public Map<String, Object> insert(@RequestBody MemberDto dto) {
		//@RequestBody : 요청 본문에 담긴 JSON 기반의 HTTP body 객체를 자바 객체로 변환
		dao.insert(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
		//JSON 형식의 객체로 return하기 위해 Map을 써줬다.
		//성공 여부를 JSON 형식으로 클라이언트에게 반환
	}
	@GetMapping("/members/{num}") // http://localhost:80/members/3
	//@GetMapping: select
	public MemberDto getData(@PathVariable("num")int num){
		//주소를 넘길 때 /3이 num에 들어간다. 
		// 1개의 데이터만 가져올 것이기 떄문에 MemberDto 앞에 List 생략
		// (위의 @RestController 덕분에) DB자료를 읽어 HTML 파일로 반환하는게 아니라 
		// JSON 형태로 변환해 클라이언트(Javascript Ajax요청)에게 반환한다.
		return dao.getData(num);  
	}
	@PutMapping("/members/{num}") //@PutMapping: update
	public Map<String, Object> update(@PathVariable("num")int num,
			//@pathVariable: 중괄호 {id}로 경로 변수를 표시하기 위해 메서드의 매개변수에 사용되며 
			//URL 경로에서 변수 값을 추출하여 매개변수에 할당한다
			@RequestBody MemberDto dto) {
		dto.setNum(num);
		dao.update(dto);
		
		return Map.of("isSuccess", true);
		//위의 1줄은 아래 3줄과 같다.
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("isSuccess", true);
//		return map;
	}
		@DeleteMapping("/members/{num}")
		public Map<String, Object> delete(@PathVariable("num")int num) {
			dao.delete(num);
			return Map.of("isSuccess", true);
	}
}

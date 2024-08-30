package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pack.model.MemberDao;

@Controller
public class InsertController {
	@Autowired
	private MemberDao memberDao;
	
	
	@ModelAttribute("command")
	public  MemberBean formBack() {
		return new MemberBean();
	}
	
	@GetMapping("insert")
	public String form() {
		return "insform";
	}
	
	@PostMapping("insert")
	//insform의 form태그 submit하고 MemberBean bean을 아래처럼 선언해주면
	//Spring MVC가 form태그의 id,name,passwd를 자동으로 MemberBean 객체에 바인딩시켜준다. 
	public String submit(MemberBean bean) {
		memberDao.insData(bean);
		return "redirect:/list";  //추가 후 목록보기
	}
}

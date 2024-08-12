package pack.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.dto.JikwonDto;
import pack.repository.GogekRepository;
import pack.repository.JikwonRepository;

@Controller
public class TestController {
	
	@Autowired
	private JikwonRepository jikwonRepo;
	

	
	@GetMapping("/")
	public String main(Model model) {
		// Entity to DTO
		List<JikwonDto> jlist = jikwonRepo.findAll()
											.stream()
											.map(JikwonDto::toDto)
											.toList();
		model.addAttribute("list", jlist);
		return "abc";
	}
	
	
	

}

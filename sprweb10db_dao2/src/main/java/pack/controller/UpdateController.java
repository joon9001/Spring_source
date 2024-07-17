package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.MemberDao;
import pack.model.MemberDto;
import pack.controller.MemberBean;

@Controller
public class UpdateController {
    @Autowired
    private MemberDao memberDao;

    @ModelAttribute("command")
    public MemberBean formBack() {
        return new MemberBean();
    }
    // 위에는 메서드가 반환하는 객체를 Model에 command라는 이름으로 추가함.
    @GetMapping("update")
    public String update(@RequestParam("id") String id, Model model) {
        MemberDto dto = memberDao.getMember(id);
        model.addAttribute("member", dto);
        return "update";
    }
    
    @PostMapping("update")
    public String submit(@ModelAttribute("member") MemberBean bean, Model model) {
        memberDao.upData(bean);
        return "redirect:/list";  // 수정 후 목록보기로 이동
    }
}

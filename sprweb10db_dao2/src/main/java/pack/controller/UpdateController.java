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
 
    @GetMapping("update")
    public String update(@RequestParam("id") String id, Model model) {
        MemberDto dto = memberDao.getMember(id);
        model.addAttribute("member", dto);
        return "update";
    }
  //위의 model.addAttribute("member", dto)와 밑의 @ModelAttribute("member") MemberBean bean 둘다
  // dto 객체의 별명을 member, MemberBean bean 객체의 별명을 member로 한다는 점에서 의미가 똑같다고 볼 수 있다.
    @PostMapping("update")
    public String submit(@ModelAttribute("member") MemberBean bean, Model model) {
        memberDao.upData(bean);
        return "redirect:/list";  // 수정 후 목록보기로 이동
    }
}

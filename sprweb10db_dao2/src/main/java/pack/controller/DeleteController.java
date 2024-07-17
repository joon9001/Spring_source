package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import pack.model.MemberDao;

@Controller
@RequestMapping("/delete")
public class DeleteController {
    @Autowired
    private MemberDao memberDao;

    @GetMapping
    public String delete(@RequestParam("id") String id) {
        memberDao.delData(id);
        return "redirect:/list";  // 삭제 후 목록보기
    }
}

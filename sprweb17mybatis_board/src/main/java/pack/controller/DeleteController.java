package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.model.BoardDao;

@Controller
public class DeleteController {
	@Autowired
	private BoardDao dao;

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteSummit(BoardBean bean) {
		boolean b = dao.deleteData(bean);
		/*
		아래 return에서 redirect를 사용하면 클라이언트 브라우저가 새로운 URL로 리다이렉트 요청을 보냅니다. 
		이 방법은 특히 데이터 변경 작업(예: 데이터 삭제, 생성, 수정) 후에 유용합니다.
		왜냐하면 forward로 데이터 변경 작업을 할 경우 새로고침 할때마다 이전 데이터가 아직 유효하여 
		삭제, 생성, 수정 작업이 계속 일어날 수 있기 때문입니다.
		반면, redirect는 완전히 새로운 HTTP 요청이므로, 이전 요청의 모든 데이터(예: 폼 데이터)는 사라집니다. 
		이는 데이터가 중복으로 전송되는 것을 방지하고, 이전 요청 데이터가 새 요청에 영향을 미치지 않도록 합니다.
		*/
		if (b) {
			return "redirect:/list";
		} else {
			return "error";
		}
	}
}

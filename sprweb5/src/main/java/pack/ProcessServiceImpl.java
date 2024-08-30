package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl implements ProcessService{
	@Autowired
	private DataDao dataDao; // 클래스의 포함관계

//	public ProcessServiceImpl() {
//		
//	}
	
	@Override
	public void selectProcess() {
		System.out.println("selectProcess 처리 시작");
		dataDao.selectData(); // 모델 영역의 클래스가 수행
		System.out.println("selectProcess 처리 끝");
		
	}
}

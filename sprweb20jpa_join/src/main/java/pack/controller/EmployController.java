package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import pack.dto.DeptDto;
import pack.dto.EmpListDto;
import pack.entity.Dept;
import pack.entity.Emp;
import pack.repository.DeptRepository;
import pack.repository.EmpRepository;

@Controller
public class EmployController {
	@Autowired
	private EntityManagerFactory factory;
	
	@Autowired
	private EmpRepository empRepo;
	
	@Autowired
	private DeptRepository deptRepo;
	
	@GetMapping("/employ/list")
	public String emplist(Model model) {
		//모든 직원 정보 출력
		//List<Emp> list = empRepo.findAll(); //기본 메서드
		//List<Emp> list = empRepo.findAllByOrderByEmpnoAsc(); //기본 메서드, 사번으로 내림차순 정렬함
		List<Emp> list = empRepo.getListAll(); // JPQL, 전체 직원 출력
		//List<Emp> list = empRepo.getListAll(1500); // JPQL, 연봉 1500 이상인 직원만 출력
		model.addAttribute("list", list);
		return "employ/elist";
	}
	@GetMapping("/employ/dept")  // 부서정보 출력
	public String emplist(@RequestParam("deptno")int deptno, Model model) {
		Dept d = deptRepo.findById(deptno).get();
		//Entity -> DTO 객체 변환을 위한 부서정보용 Service 파일을 만들어야하는데 생략하고 여기에 모두 썼다.
		//Dept 파일에서 @Id로 primary key가 된 deptno를 findById로 부서 정보를 가져와 d에 저장한다.
		DeptDto dto = DeptDto.toDto(d);
		model.addAttribute("dto", dto);
		return "employ/dept"; 
	}
	
	// JPQL 연습장 관련
	@GetMapping("/jpql")
	public String jpql() {
		return "jpql";
	}
	
	@ResponseBody //넘어온 내용을 Json 타입으로 return함
	@PostMapping("/jpql/test")
	public List<EmpListDto> test(@RequestParam("query")String query){
		//System.out.println(query);  // select e from Emp as e
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<EmpListDto> list = null;
		try {
			// 전달받은 쿼리(JPQL)문을 실행
			//entity를 Dto로 변환
			TypedQuery<Emp> tQuery = em.createQuery(query, Emp.class);
	//TypedQuery를 사용하면 반환되는 결과의 타입을 명확히 지정할 수 있어 컴파일 시 타입 체크가 가능. 
	//이는 타입 캐스팅 오류를 방지하고 코드의 안정성을 높임.
	//TypedQuery<Emp>는 Emp 엔티티 타입의 결과를 반환하도록 보장함.
			//위의 첫 번째 인자는 JPQL 쿼리 문자열이고, 두 번째 인자는 반환될 결과 타입 (Emp 클래스)이다.
			//위에서 Emp.class를 썼으므로 Emp 테이블만 쿼리에 사용 가능하다
			list = tQuery.getResultStream().map(EmpListDto::toDto).toList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return list;
	}
}

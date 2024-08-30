package pack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import other.OtherClass;

@SpringBootApplication
@ComponentScan(basePackages = {"other"})
//pack.model아닌 other 패키지로 바꿔 pack의 하위 패키지가 아니라 동등한 관계로 바뀔경우 
//componentscan으로 패키지명을 지정해줘야 한다.
public class Sprweb13jpaBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sprweb13jpaBasicApplication.class, args)
		.getBean(Sprweb13jpaBasicApplication.class).myExecute();
	}
	
	@Autowired
	ProductCrudRepository crudRepository;
	
	@Autowired
	OtherClass class1;
	
	private void myExecute() {
		System.out.println("독립적인 프로그램으로 실행");
		insData();
		delData();
		selectData();
		
		class1.abc();
	}
	
	private void insData() {
		//ProductVo productVo = new ProductVo();
		//productVo.setCode(4);
		//...
		ProductVo productVo = new ProductVo(2, "사랑비", 10, 2000);
		//생성자를 이용한 데이터 입력
		//System.out.println(productVo.toString());
		crudRepository.save(productVo);
		//save를 하면 값이 없을 경우 insert가 되고 값이 있을 경우 update가 된다.
	}
	
	private void delData() {
		crudRepository.deleteById(3);
	}
	
	private void selectData() {
		//전체 레코드 읽기
		//아래는 CrudRepository가 지원하는 추상 메소드 중 하나 (메소드 구현/오버라이딩은 내부적으로 진행됨)
		List<ProductVo> list = (List<ProductVo>)crudRepository.findAll();
		//System.out.println(list.get(0).getSang());
		for(ProductVo p:list) {
			System.out.println(p.getCode() + " " +
					p.getSang() + " " +
					p.getSu() + " " +
					p.getDan());
		}
		System.out.println();
		//1개 레코드 읽기
		//여기서 findById는 primary key로 설정된 code를 기준으로 한다.
		ProductVo vo = crudRepository.findById(2).get();
		System.out.println(vo.getCode() + " " +
					vo.getSang() + " " +
					vo.getSu() + " " +
					vo.getDan());
	}
}

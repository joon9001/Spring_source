package pack.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dept {
	@Id
	private int deptno;
	private String dname;
	private String loc;
	
	// FetchType.LAZY : Dept 사용 중 Emp는 필요할 때 로딩(지연 로딩)
	// 단, LAZY타입은 세션이 열려있는 동안 세션관리가 필요하며 LazyInitializationException 조치가 필요 
	// FetchType.EAGER : Dept 사용 중 Emp는 필요할 때 즉시 로딩
	//1개의 department(dept)에 여러명의 employee(Emp)가 있으므로 1대다 관계인 @onetomany
	@OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)
	//Emp 엔티티의 dept 필드가 관계의 주인임을 나타내며, 외래 키가 Emp 테이블에 존재한다는 것을 의미합니다.
	@Builder.Default // Emp 엔티티가 생성될 때 list를 초기화함.
	//다른 파일에서 getlist()를 쓸 수 있음
	private List<Emp> list = new ArrayList<Emp>();
}

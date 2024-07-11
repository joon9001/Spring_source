package pack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//위에는 롬복, 아래는 hibernate
@Entity     // DB의 특정 테이블과 매핑
@Table(name="mem")
public class MemDto {  // 카맬 케이스로 작성하면 자동으로 언더스코어 네이밍(Mem_Dto) 컨벤션을 따름
	@Id //pk 칼럼 위에는 @id를 붙여준다. 
	@Column(name="num") //만약 아래 멤버필드 변수명이 DB 테이블의 칼럼명과 다를 경우 @column으로 DB칼럼명을 써주면 된다.
	private int num;
	@Column(name="name", nullable = true) // 바로 아래 칼럼이 null을 허용할 경우 true
	private String name;
	
	private String addr;

}

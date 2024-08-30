package pack;

import java.sql.Date;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="jikwon")

public class Jikwon {
	@Id
	@Column(name="jikwon_no")
	private int jikwonNo;
	
	@Column(name="jikwon_name")
	private String jikwonName;
	
	@ManyToOne // 다 대 1 관계
	@JoinColumn(name="buser_num", referencedColumnName = "buser_no")
	//jikwon 테이블과 buser 테이블이 join하여 
	//jikwon 테이블의 buser_num은 buser 테이블의 buser_no를 참조한다는 뜻
	private Buser buser;
	
	@Column(name="jikwon_ibsail")
	private Date jikwonIbsail;
}

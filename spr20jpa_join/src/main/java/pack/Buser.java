package pack;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="buser")
public class Buser {
	@Id
	@Column(name="buser_no")//실제 DB 칼럼명과 JPA 칼럼명이 다를 경우 DB 칼럼명을 name으로 적어준다.
	private int buserNo;
	@Column(name="buser_name")
	private String buserName;
	
}

package pack.entity;

import java.util.Date;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Emp {
	@Id
	private Integer	empno; 
	private String ename; 
	private String job;
	private Integer mgr; //manager, 관리자 직원번호
	@Temporal(TemporalType.DATE) //날짜 타입 매핑 시 사용
	private Date hiredate;
	private Double sal;
	private Double comm;
	//private Integer deptno;
	
	@ManyToOne(fetch = FetchType.EAGER)  //즉시 로딩
	@JoinColumn(name = "deptno", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	//데이터베이스에 외래 키 제약 조건을 생성하지 않도록 JPA에 지시. 
	private Dept dept;
}

package pack.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pack.dto.MemberDto;

@Getter
@Setter
@Builder //@builder를 쓸 때는 @Data 대신 @getter/setter를 쓰자 (@builder와 @Data의 충돌 가능성 때문)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEMBER_TBL")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // 번호 자동증가
	private Long num;
	private String name;
	private String addr;
	
	//dto를 entity로 변환하기
	public static Member toEntity(MemberDto dto) {
		return Member.builder()
				.num(dto.getNum())
				.name(dto.getName())
				.addr(dto.getAddr())
				.build();
	}
}

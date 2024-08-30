package pack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Gogek {

	@Id
    @Column(name="gogek_no")
    private int gno;

    @Column(name="gogek_name")
    private String gname;

    @Column(name="gogek_tel")
    private String gtel;
    
    @Column(name="gogek_jumin")
    private String jumin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gogek_damsano")
    private Jikwon jikwon;
    //gogek_damsano를 통해 jikwon 테이블과 fetch join을 함으로써 gogek 객체에서 jikwon field 멤버를 쓸 수 있게 된다.
}

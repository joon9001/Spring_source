package pack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SangpumImpl extends JdbcDaoSupport implements SangpumInter{
	
	//JdbcDaoSuppor 클래스는 Spring 프레임워크에서 제공하는 추상 클래스
	//데이터베이스와 상호 작용하는 DAO를 개발할 떄 편리한 기능을 제공.
	//이 클래스는 JdbcTemplate을 사용하여 데이터베이스 작업을 처리
	//템플릿 메소드 패턴을 활용하여 일반적인 데이터 엑세스 작업을 구현.
	
	//JDBCDaoSupport의 멤버 메소드 중
	//getJdbcTemplate() 
	//setDataSource() // DB 데이터를 읽어오는 녀석
	
	@Override
	public ArrayList<SangpumDto> selectAll() {
		RowMapper rowMapper = new SangpumRowMapper();
		
		return (ArrayList)getJdbcTemplate().query("select * from sangdata", rowMapper);
	}
	
	// 내부 클래스 
	class SangpumRowMapper implements RowMapper{
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// PreparedStatement에 의해 select의 실행 결과가 mapRow로 전달됨. rs.next() x
			System.out.println("rowNum : " + rowNum);
			SangpumDto dto = new SangpumDto();
			dto.setCode(rs.getString("code"));
			dto.setSang(rs.getString("sang"));
			dto.setSu(rs.getString("su"));
			dto.setDan(rs.getString("dan"));
			return dto;
			
	//rowMapper에 의해 dto가 ListCollection에 저장됨. 레코드가 끝날 때까지, 
	//rs.next()에서 더이상 자료가 없어 false가 될 경우, 위의 selectAll()의 return 값이 List 타입으로 반환된다.
		}
		
	}
}

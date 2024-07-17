package pack.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.model.JikwonDto;

@Repository
public class DataDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Autowired
	private DataSource dataSource;
	
	public DataDao() {
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<JikwonDto> selectAll(String jikwonJik) {
		ArrayList<JikwonDto> list = new ArrayList<JikwonDto>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from jikwon where jikwon_jik = ?");
			
			pstmt.setString(1, jikwonJik);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JikwonDto dto = new JikwonDto();
				dto.setJikwon_no(rs.getString("jikwon_no"));
				dto.setJikwon_name(rs.getString("jikwon_name"));
				dto.setJikwon_gen(rs.getString("jikwon_gen"));
				dto.setJikwon_pay(rs.getString("jikwon_pay"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("selectAll err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
}

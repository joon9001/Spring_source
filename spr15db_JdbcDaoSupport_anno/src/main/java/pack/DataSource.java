package pack;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component //init.xml에서 써야할 datasource DB 연결을 클래스파일로 따로 만듬
public class DataSource extends DriverManagerDataSource{
	public DataSource() {
		setDriverClassName("org.mariadb.jdbc.Driver");
		setUrl("jdbc:mariadb://localhost:3306/test");
		setUsername("root");
		setPassword("123");
	}
	
}

package pack.mybatis;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pack.model.SqlMapper_Inter;

public class SqlMapConfig {
	public static SqlSessionFactory sqlSessionFactory; // DB의 SQL명령을 실행시킬 때 필요한 메소드를 갖고 있다.

	static {
		String resource = "pack/mybatis/Configuration.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);   
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); 
			reader.close();
			
			// MyBatis Annotation 사용시 추가. Factory에 넣어줘야 함
			Class[] mappers = {SqlMapper_Inter.class};
			for(Class cl:mappers) {
				// Mapper 등록
				sqlSessionFactory.getConfiguration().addMapper(cl);
			}
		
		} catch (Exception e) {
			System.out.println("SqlMapConfig 오류 : " + e);
		}
	}

	public static SqlSessionFactory getSqlSession() {
		return sqlSessionFactory;
	}
}
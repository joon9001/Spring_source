package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import pack.controller.FormBean;

@Mapper //sqlmapconfig 대체
public interface DataMappingInterface {
	@Select("select * from sangdata")
	List<SangpumDto> selectAll();
	
	//searchValue가 포함된 구문을 출력하는 sql 구문
	@Select("select * from sangdata where sang like concat('%',#{searchValue},'%')")
	//@Select("select * from sangdata where sang like '%'||#{searchValue}||'%'")  <-- 위와 동일한 기능이나 이건 oracle용
	List<SangpumDto> selectSearch(FormBean bean);
	
}

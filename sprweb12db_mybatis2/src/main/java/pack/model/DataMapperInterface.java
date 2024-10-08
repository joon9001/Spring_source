package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pack.controller.MemBean;

@Mapper	 // 마커 인터페이스 
public interface DataMapperInterface {
	@Select("Select * from mem")
	List<MemDto> selectAll();
	
	@Select("Select * from mem where num=#{num}")
	MemDto selectPart(String num);
	//하나만 읽을 것이기 때문에 List가 필요없다.
	@Insert("insert into mem values(#{num},#{name},#{addr})")
	int insertData(MemBean bean);
	
	@Update("update mem set name=#{name},addr=#{addr} where num=#{num}")
	int updateData(MemBean bean);

	@Delete("delete from mem where num=#{num}")
	int deleteData(String num);
}

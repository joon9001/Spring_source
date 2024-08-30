package pack.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
public class DataDao {
	@Autowired
	private DataMappingInterface mappingInterface;
	
	public List<JikwonDto> getDataAll(String jikwon_jik){
		List<JikwonDto> list = mappingInterface.selectDatas(jikwon_jik);
		return list;
	}
	
}

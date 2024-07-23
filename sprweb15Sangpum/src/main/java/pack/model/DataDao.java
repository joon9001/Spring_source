package pack.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataDao {
	@Autowired
	private SangpumRepository repository;
	
	// 전체 자료 읽기
	public List<Sangpum> getDataAll(){
		List<Sangpum> list = repository.findAll(); // 기본 메서드 
		return list;
	}
	
	// 검색 자료 읽기
	public List<Sangpum> getDataSearch(String svalue){
		//List<Sangpum> list = repository.findBySangContaining(svalue); //검색어가 포함된 : like '%검색어%'

		List<Sangpum> list = repository.searchLike(svalue); //JPQL 사용
		System.out.println("list : " + list.size());
		return list;
	
	}
}

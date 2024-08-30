package pack;

import org.springframework.data.repository.CrudRepository;

public interface ProductCrudRepository extends CrudRepository<ProductVo, Integer>{
	// CrudRepository는 save(), findById(), count() 등의 함수를 지원함
}

package pack.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class DataImpl implements DataInterface{
	
	public List<MemDto> selectDataAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");     
		EntityManager em = emf.createEntityManager(); // 엔티티의 생명주기 관리, CRUD를 수행
		EntityTransaction tx = em.getTransaction(); // Transaction을 관리하는 인터페이스
	
		List<MemDto> list = null;
		
		try {
			// 레코드 추가 
			/*
			tx.begin();
			MemDto dto1 = new MemDto();
			dto1.setNum(4);
			dto1.setName("고길동");
			dto1.setAddr("서초구 방배동");
			em.persist(dto1);
			tx.commit();
			*/
			// Hibernate: /* insert pack.model.MemDto */
			// 실제 SQL 처리 : insert into mem (addr, name, num) values(?,?,?)
			
			//레코드 수정
			/*
			tx.begin();
			MemDto dto2 = em.find(MemDto.class, 4);
			dto2.setName("신길동");
			tx.commit();
			*/
			//Hibernate : memdto0_.num as num1_0_, memdto0_.addr as addr2_0_, memdto0_.name as name3_0_ from mem memdto0_
			//Hibernate: update pack.model.MemDto
			//update mem set addr=?, name=? where num=?
			
			// 레코드 삭제
			/*
			tx.begin();
			MemDto dto3 = em.find(MemDto.class, 4);
			em.remove(dto3);
			tx.commit();
			*/
			
			System.out.println("부분 자료 읽기 (단일 엔티티) find() 메소드 사용-----");
			//public find(Class<T> entityClass, Object pk);
			MemDto mdto = em.find(MemDto.class, 2);
			//1일 경우 유비, 2일 경우 관우, 3일 경우 장비가 찍힌다.
			System.out.println(mdto.getNum() + " " +
					mdto.getName() + " " +
					mdto.getAddr());
			
			System.out.println("부분 자료 읽기 (복수 엔티티)");
			List<MemDto> listPart = findByAddr(em, "강남");
			for(MemDto m : listPart) {
				System.out.println(m.getNum() + " " +
						m.getName() + " " +
						m.getAddr());
			}	
				
			System.out.println("\n전체 자료 읽기 (JPQL 사용)-----");

			/*
			list = findAll(em, MemDto.class);
			for(MemDto m : list) {
				System.out.println(m.getNum() + " " +
						m.getName() + " " +
						m.getAddr());
			}
			*/
		list = em.createQuery("select e from MemDto as e", MemDto.class).getResultList();
		// Hibernate ==> select * from MemDto e, 이 친구는 RDMS의 종류에 관계없이 공통적으로 사용
		//Hibernate가 DbDialect를 보고 실제 SQL문으로 변환 ==> 아래와 같은 실제 SQL문으로 변환
		//select memdto0_.num as num1_0_, memdto0_.addr as addr2_0_, memdto0_.name as name3_0_ from mem memdto0_
		} catch (Exception e) {
			tx.rollback();
			System.out.println("err : " + e);
		} finally {
			em.close();
			emf.close();
		}

		return list;
	}
	//부분 자료(복수)를 읽기 위한 메소드
	public List<MemDto> findByAddr(EntityManager em, String ss){
		// addr 필드가 특정 접두사로 시작하는 레코드 읽기 
		String jpql = "SELECT m FROM MemDto m where m.addr LIKE :ss";
		//ss가 들어간 주소를 가진 MemDto의 별칭 m을 모두 선택
		
		TypedQuery<MemDto> query = em.createQuery(jpql, MemDto.class);
		// TypedQuery<entity> query = em.createQuery(jpql, entity 클래스)
		// JPQL을 작성하고 실행하는 역할
		query.setParameter("ss", ss + "%"); // SQL의 Like 연산 검색문자%
		return query.getResultList();
	}
	
	
	
	//전체 자료를 읽기 위한 메소드
	public<T> List<T> findAll(EntityManager em, Class<T> cla){
		//JPQL
		
		return em.createQuery("select e from " + 
			cla.getSimpleName() + " e", cla).getResultList();
	}
}

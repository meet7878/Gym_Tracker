package com.repository.admin;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bean.DietBean;


@Repository
public interface DietRepository extends CrudRepository<DietBean, Integer> {
	List<DietBean> findAll();
	
	DietBean findByDietId(Integer dietId);
}
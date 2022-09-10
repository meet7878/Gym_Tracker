package com.repository.admin;

import org.springframework.data.repository.CrudRepository;

import com.bean.admin.WorkOutBean;

public interface WorkoutRepository extends CrudRepository<WorkOutBean,Integer> {

}

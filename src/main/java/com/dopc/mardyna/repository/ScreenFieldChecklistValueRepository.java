package com.dopc.mardyna.repository;

import com.dopc.mardyna.entity.ScreenFieldChecklistValue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenFieldChecklistValueRepository extends JpaRepository<ScreenFieldChecklistValue, Long> {
	
	List<ScreenFieldChecklistValue> findByField(Long field);
}

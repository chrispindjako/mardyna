package com.dopc.mardyna.repository;

import com.dopc.mardyna.entity.ScreenField;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenFieldRepository extends JpaRepository<ScreenField, Long> {
	
	List<ScreenField> findByScreen(Long screen);
}

package com.dopc.mardyna.repository;

import com.dopc.mardyna.entity.Screen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {

	Screen findByName(String name);
	
	List<Screen> findByEntity(Long entity);

}

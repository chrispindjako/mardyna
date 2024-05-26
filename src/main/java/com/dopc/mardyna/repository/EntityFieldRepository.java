package com.dopc.mardyna.repository;

import com.dopc.mardyna.entity.EntityField;
import com.dopc.mardyna.entity.EntitySchema;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityFieldRepository extends JpaRepository<EntityField, Long> {
	
	List<EntityField> findByEntitySchema(EntitySchema entitySchema);
}

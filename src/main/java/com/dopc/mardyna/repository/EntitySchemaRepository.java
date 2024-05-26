package com.dopc.mardyna.repository;

import com.dopc.mardyna.entity.EntitySchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntitySchemaRepository extends JpaRepository<EntitySchema, Long> {

	EntitySchema findByName(String name);
}

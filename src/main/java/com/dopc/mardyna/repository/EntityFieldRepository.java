package com.dopc.mardyna.repository;

import com.dopc.mardyna.entity.EntityField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityFieldRepository extends JpaRepository<EntityField, Long> {
}

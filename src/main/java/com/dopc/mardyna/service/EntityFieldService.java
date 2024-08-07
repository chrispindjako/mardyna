package com.dopc.mardyna.service;

import com.dopc.mardyna.entity.EntityField;
import com.dopc.mardyna.repository.EntityFieldRepository;
import com.dopc.mardyna.util.QueryBuilder;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EntityFieldService {

    @Autowired
    private EntityFieldRepository repository;
    
    @Autowired
    private EntityManager entityManager;
    
    public EntityField save(EntityField entityField) {
        return repository.save(entityField);
    }

    public EntityField update(EntityField entityField) {
        return repository.save(entityField);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public EntityField findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
	public List<EntityField> findByEntity(Long entity) {
		return repository.findByEntity(entity);
	}

    public Page<EntityField> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    public List<HashMap<String, Object>> search(QueryBuilder queryBuilder) throws Exception {
        return QueryBuilder.QueryResult(queryBuilder, "ENTITY_FIELD", EntityField.class, entityManager);
    }


}

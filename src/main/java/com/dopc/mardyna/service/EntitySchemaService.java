package com.dopc.mardyna.service;


import com.dopc.mardyna.entity.EntitySchema;
import com.dopc.mardyna.repository.EntitySchemaRepository;
import com.dopc.mardyna.util.QueryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

import java.util.HashMap;
import java.util.List;

@Service
public class EntitySchemaService {

    @Autowired
    private EntitySchemaRepository repository;
    
    @Autowired
    private EntityManager entityManager;

    public EntitySchema save(EntitySchema entitySchema) {
        return repository.save(entitySchema);
    }

    public EntitySchema update(EntitySchema entitySchema) {
        return repository.save(entitySchema);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public EntitySchema findById(Long id) {
        return repository.findById(id).orElse(null);
    }

	public EntitySchema findByName(String name) {
		return repository.findByName(name);
	}
	
    public Page<EntitySchema> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    public List<HashMap<String, Object>> search(QueryBuilder queryBuilder) throws Exception {
        return QueryBuilder.QueryResult(queryBuilder, "ENTITY_SCHEMA", EntitySchema.class, entityManager);
    }

	public EntitySchema generate(Long id) {
		EntitySchema entitySchema =  findById(id);
		entitySchema.setIsgenerated(true);
		return save(entitySchema);
	}
	
	public EntitySchema unGenerate(Long id) {
		EntitySchema entitySchema =  findById(id);
		entitySchema.setIsgenerated(false);
		return save(entitySchema);
	}

}

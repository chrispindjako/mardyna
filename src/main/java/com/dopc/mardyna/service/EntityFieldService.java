package com.dopc.mardyna.service;

import com.dopc.mardyna.entity.EntityField;
import com.dopc.mardyna.entity.EntitySchema;
import com.dopc.mardyna.repository.EntityFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityFieldService {

    @Autowired
    private EntityFieldRepository repository;
    
    @Autowired
    private EntitySchemaService entitySchemaService;

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
    
	public List<EntityField> findByEntitySchema(String entityName) {
		EntitySchema entitySchema =  entitySchemaService.findByName(entityName);
		return repository.findByEntitySchema(entitySchema);
	}

    public Page<EntityField> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    public List<EntityField> advancedSearch(String criteria) {
        // Implement advanced search logic here
        return List.of(); // Example
    }


}

package com.dopc.mardyna.service;

import com.dopc.mardyna.entity.ScreenFieldChecklistValue;
import com.dopc.mardyna.repository.ScreenFieldChecklistValueRepository;
import com.dopc.mardyna.util.QueryBuilder;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ScreenFieldChecklistValueService {

    @Autowired
    private ScreenFieldChecklistValueRepository repository;
    
    @Autowired
    private EntityManager entityManager;
    
    public ScreenFieldChecklistValue save(ScreenFieldChecklistValue value) {
        return repository.save(value);
    }

    public ScreenFieldChecklistValue update(ScreenFieldChecklistValue value) {
        return repository.save(value);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ScreenFieldChecklistValue findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
	public List<ScreenFieldChecklistValue> findByField(Long field) {
		return repository.findByField(field);
	}

    public Page<ScreenFieldChecklistValue> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    public List<HashMap<String, Object>> search(QueryBuilder queryBuilder) throws Exception {
        return QueryBuilder.QueryResult(queryBuilder, "SCREEN_FIELD_CHECK_LIST_VALUE", ScreenFieldChecklistValue.class, entityManager);
    }


}

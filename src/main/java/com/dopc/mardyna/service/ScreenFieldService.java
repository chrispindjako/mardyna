package com.dopc.mardyna.service;

import com.dopc.mardyna.entity.ScreenField;
import com.dopc.mardyna.repository.ScreenFieldRepository;
import com.dopc.mardyna.util.QueryBuilder;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ScreenFieldService {

    @Autowired
    private ScreenFieldRepository repository;
    
    @Autowired
    private EntityManager entityManager;
    
    public ScreenField save(ScreenField screenField) {
        return repository.save(screenField);
    }

    public ScreenField update(ScreenField screenField) {
        return repository.save(screenField);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ScreenField findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
	public List<ScreenField> findByScreen(Long screen) {
		return repository.findByScreen(screen);
	}

    public Page<ScreenField> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    public List<HashMap<String, Object>> search(QueryBuilder queryBuilder) throws Exception {
        return QueryBuilder.QueryResult(queryBuilder, "SCREEN_FIELD", ScreenField.class, entityManager);
    }


}

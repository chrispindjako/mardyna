package com.dopc.mardyna.service;

import com.dopc.mardyna.entity.Screen;
import com.dopc.mardyna.repository.ScreenRepository;
import com.dopc.mardyna.util.QueryBuilder;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository repository;

    @Autowired
    private EntityManager entityManager;
    
    public Screen save(Screen screen) {
        return repository.save(screen);
    }

    public Screen update(Screen screen) {
        return repository.save(screen);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Screen findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
	public List<Screen> findByEntity(Long entity) {
		return repository.findByEntity(entity);
	}

	public Screen findByName(String name) {
		return repository.findByName(name);
	}
	
    public Page<Screen> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    public List<HashMap<String, Object>> search(QueryBuilder queryBuilder) throws Exception {
        return QueryBuilder.QueryResult(queryBuilder, "SCREEN", Screen.class, entityManager);
    }


}

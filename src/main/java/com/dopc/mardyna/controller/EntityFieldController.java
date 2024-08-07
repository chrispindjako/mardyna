package com.dopc.mardyna.controller;

import com.dopc.mardyna.entity.EntityField;
import com.dopc.mardyna.service.EntityFieldService;
import com.dopc.mardyna.util.QueryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/entity-fields")
public class EntityFieldController {

    @Autowired
    private EntityFieldService service;

    @PostMapping
    public EntityField create(@RequestBody EntityField entityField) {
        return service.save(entityField);
    }

    @PutMapping("/{id}")
    public EntityField update(@PathVariable Long id, @RequestBody EntityField entityField) {
        entityField.setId(id);
        return service.update(entityField);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public EntityField findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @GetMapping("/entity/{id}")
    public List<EntityField> findByEntity(@PathVariable Long id) {
        return service.findByEntity(id);
    }

    @GetMapping
    public Page<EntityField> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }
    
    @PostMapping("/search")
    public List<HashMap<String, Object>> search(@RequestBody QueryBuilder queryBuilder) throws Exception {
        return service.search(queryBuilder);
    }
    
    
}

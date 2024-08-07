package com.dopc.mardyna.controller;

import com.dopc.mardyna.entity.EntitySchema;
import com.dopc.mardyna.service.EntitySchemaService;
import com.dopc.mardyna.util.QueryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/entity-schemas")
public class EntitySchemaController {

    @Autowired
    private EntitySchemaService service;

    @PostMapping
    public EntitySchema create(@RequestBody EntitySchema entitySchema) {
        return service.save(entitySchema);
    }

    @PutMapping("/{id}")
    public EntitySchema update(@PathVariable Long id, @RequestBody EntitySchema entitySchema) {
        entitySchema.setId(id);
        return service.update(entitySchema);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public EntitySchema findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Page<EntitySchema> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }
    
    @PostMapping("/search")
    public List<HashMap<String, Object>> search(@RequestBody QueryBuilder queryBuilder) throws Exception {
        return service.search(queryBuilder);
    }
    
    @PostMapping("/count")
    public Integer count(@RequestBody QueryBuilder queryBuilder) throws Exception {
        return service.count(queryBuilder);
    }
    
    @GetMapping("/synchronize/{id}")
    public EntitySchema synchronize(@PathVariable Long id) {
        return service.synchronize(id);
    }
    
}

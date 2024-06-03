package com.dopc.mardyna.controller;

import com.dopc.mardyna.entity.EntitySchema;
import com.dopc.mardyna.service.EntitySchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping("/search")
    public List<EntitySchema> advancedSearch(@RequestParam String criteria) {
        return service.advancedSearch(criteria);
    }
    
    @GetMapping("/generate/{id}")
    public EntitySchema generate(@PathVariable Long id) {
        return service.generate(id);
    }
    
    @GetMapping("/ungenerate/{id}")
    public EntitySchema unGenerate(@PathVariable Long id) {
        return service.unGenerate(id);
    }
}

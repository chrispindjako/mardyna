package com.dopc.mardyna.controller;

import com.dopc.mardyna.entity.ScreenFieldChecklistValue;
import com.dopc.mardyna.service.ScreenFieldChecklistValueService;
import com.dopc.mardyna.util.QueryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/screen-field-checklist-value")
public class ScreenFieldChecklistValueController {

    @Autowired
    private ScreenFieldChecklistValueService service;

    @PostMapping
    public ScreenFieldChecklistValue create(@RequestBody ScreenFieldChecklistValue value) {
        return service.save(value);
    }

    @PutMapping("/{id}")
    public ScreenFieldChecklistValue update(@PathVariable Long id, @RequestBody ScreenFieldChecklistValue value) {
        value.setId(id);
        return service.update(value);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ScreenFieldChecklistValue findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @GetMapping("/field/{id}")
    public List<ScreenFieldChecklistValue> findByField(@PathVariable Long id) {
        return service.findByField(id);
    }

    @GetMapping
    public Page<ScreenFieldChecklistValue> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }
    
    @PostMapping("/search")
    public List<HashMap<String, Object>> search(@RequestBody QueryBuilder queryBuilder) throws Exception {
        return service.search(queryBuilder);
    }
    
    
}

package com.dopc.mardyna.controller;

import com.dopc.mardyna.entity.ScreenField;
import com.dopc.mardyna.service.ScreenFieldService;
import com.dopc.mardyna.util.QueryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/screen-fields")
public class ScreenFieldController {

    @Autowired
    private ScreenFieldService service;

    @PostMapping
    public ScreenField create(@RequestBody ScreenField field) {
        return service.save(field);
    }

    @PutMapping("/{id}")
    public ScreenField update(@PathVariable Long id, @RequestBody ScreenField field) {
        field.setId(id);
        return service.update(field);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ScreenField findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @GetMapping("/screen/{id}")
    public List<ScreenField> findByScreen(@PathVariable Long id) {
        return service.findByScreen(id);
    }

    @GetMapping
    public Page<ScreenField> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }	
    
    @PostMapping("/search")
    public List<HashMap<String, Object>> search(@RequestBody QueryBuilder queryBuilder) throws Exception {
        return service.search(queryBuilder);
    }
    
    
}

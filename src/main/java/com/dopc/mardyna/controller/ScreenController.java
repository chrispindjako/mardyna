package com.dopc.mardyna.controller;

import com.dopc.mardyna.entity.Screen;
import com.dopc.mardyna.service.ScreenService;
import com.dopc.mardyna.util.QueryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/screens")
public class ScreenController {

    @Autowired
    private ScreenService service;

    @PostMapping
    public Screen create(@RequestBody Screen screen) {
        return service.save(screen);
    }

    @PutMapping("/{id}")
    public Screen update(@PathVariable Long id, @RequestBody Screen screen) {
    	screen.setId(id);
        return service.update(screen);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Screen findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @GetMapping("/entity/{id}")
    public List<Screen> findByEntity(@PathVariable Long id) {
        return service.findByEntity(id);
    }

    @GetMapping
    public Page<Screen> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }
    
    @PostMapping("/search")
    public List<HashMap<String, Object>> search(@RequestBody QueryBuilder queryBuilder) throws Exception {
        return service.search(queryBuilder);
    }
    
}

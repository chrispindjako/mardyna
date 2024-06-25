package com.dopc.mardyna.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dopc.mardyna.service.DataService;
import com.dopc.mardyna.util.QueryBuilder;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/data")
public class DataController {
    
    @Autowired
    private DataService entityService;
    
    @PostMapping("search")
    public ResponseEntity<?> search(@RequestParam String entityName, @RequestBody QueryBuilder queryBuilder) throws Exception {
        List<?> entities = entityService.search(entityName, queryBuilder);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
    
    @PostMapping("count")
    public ResponseEntity<?> count(@RequestParam String entityName, @RequestBody QueryBuilder queryBuilder) throws Exception {
        int count = entityService.count(entityName, queryBuilder);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<?> createData(@RequestParam String entityName, @RequestBody QueryBuilder queryBuilder) throws ClassNotFoundException, SQLException {
    	Object createdObject = entityService.create(entityName, queryBuilder);
        return new ResponseEntity<>(createdObject, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateData(@RequestParam String entityName, @PathVariable Long id, @RequestBody QueryBuilder queryBuilder) throws ClassNotFoundException, SQLException {
        Object updatedObject = entityService.update(entityName, id, queryBuilder);
        return new ResponseEntity<>(updatedObject, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteData(@RequestParam String entityName, @PathVariable Long id) throws ClassNotFoundException, SQLException {
        entityService.delete(entityName, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

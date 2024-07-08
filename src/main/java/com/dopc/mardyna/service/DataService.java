package com.dopc.mardyna.service;

import java.sql.SQLException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dopc.mardyna.dao.SqlExecuteGenerateQuery;
import com.dopc.mardyna.entity.EntityField;
import com.dopc.mardyna.entity.EntitySchema;
import com.dopc.mardyna.util.MatchBuilder;
import com.dopc.mardyna.util.QueryBuilder;
import com.dopc.mardyna.util.MatchBuilder.MATCH_OPERATOR;

@Service
public class DataService {

    @Autowired
    private EntityFieldService entityFieldService;
    
    @Autowired
    private EntitySchemaService entitySchemaservice;
    
    @Autowired
    private SqlExecuteGenerateQuery sqlExecuteGenerateQuery;

   
    public List<?> search(String entityName, QueryBuilder queryBuilder) throws Exception {
    	EntitySchema entitySchema = entitySchemaservice.findByName(entityName);
    	List<EntityField> fields = entityFieldService.findByEntity(entitySchema.getId());
        return sqlExecuteGenerateQuery.selectOperation(entityName, fields, queryBuilder);
    }	
    

    private Object findById(Long id, String entityName, List<EntityField> fields, QueryBuilder queryBuilder) throws ClassNotFoundException, SQLException {
    	List<MatchBuilder> matchs = new ArrayList<MatchBuilder>();
    	MatchBuilder match = new MatchBuilder();
    	match.setName("id");
    	match.setOperator(MATCH_OPERATOR.IS);
    	match.setValue(id);
		matchs.add(match);
		queryBuilder.setMatchs(matchs );
    	List<?> list = sqlExecuteGenerateQuery.selectOperation(entityName, fields, queryBuilder);
		return list.get(0);
	}
    
    public int count(String entityName, QueryBuilder queryBuilder) throws Exception {
        return sqlExecuteGenerateQuery.selectCountOperation(entityName, queryBuilder);
    }	
    
    public Object create(String entityName, QueryBuilder queryBuilder) throws ClassNotFoundException, SQLException {
    	EntitySchema entitySchema = entitySchemaservice.findByName(entityName);
    	List<EntityField> fields = entityFieldService.findByEntity(entitySchema.getId());
    	Long id = sqlExecuteGenerateQuery.insertOperation(entityName, fields, queryBuilder);
    	return findById(id, entityName, fields, queryBuilder);
    }
    
    public Object update(String entityName, Long id, QueryBuilder queryBuilder) throws ClassNotFoundException, SQLException {
    	EntitySchema entitySchema = entitySchemaservice.findByName(entityName);
    	List<EntityField> fields = entityFieldService.findByEntity(entitySchema.getId());
    	sqlExecuteGenerateQuery.updateOperation(entityName, fields, id, queryBuilder);
    	return findById(id, entityName, fields, queryBuilder);
    }

    public void delete(String entityName, Long id) throws ClassNotFoundException, SQLException {
    	sqlExecuteGenerateQuery.deleteOperation(entityName,id);
    }
  
    
    
}

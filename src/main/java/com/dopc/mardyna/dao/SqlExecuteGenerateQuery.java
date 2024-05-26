package com.dopc.mardyna.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dopc.mardyna.entity.EntityField;
import com.dopc.mardyna.util.QueryBuilder;

@Service
public class SqlExecuteGenerateQuery {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.url}")
	private String jdbcUrl;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		// Register the PostgreSQL driver
        Class.forName(driverClassName);
		return DriverManager.getConnection(jdbcUrl, username, password);
	}
	
	private void closeConnection(ResultSet resultSet,PreparedStatement preparedStatement, Connection connection) throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
        preparedStatement.close();
        connection.close();
	}
	
    public List<Map<String, Object>> selectOperation (String entityName, List<EntityField> fields, QueryBuilder queryBuilder) throws SQLException, ClassNotFoundException{
		fields.add(new EntityField("id"));
    	// Connect to the database
        Connection connection = getConnection();
        // Calculer l'offset
        Long offset = null;
        Integer limit = null;
        if (queryBuilder.getLimit() > 0) {
        	limit = queryBuilder.getLimit();
		}
        if (limit != null && queryBuilder.getPage() > 0) {
        	offset = (queryBuilder.getPage() - 1) * limit;
		}
        
        String p = (queryBuilder.getProject() == null || queryBuilder.getProject().isEmpty()) ? "*" : queryBuilder.getProjectListString();
		String q = "SELECT " + p + " FROM " + entityName + 
				queryBuilder.generateWhereClause() + 
				(queryBuilder.getOrderby() != null ? " ORDER BY " + queryBuilder.getOrderby() + " " + queryBuilder.getOrderby() : " ") + 
				(limit != null ? " LIMIT " + limit + " " : "") + 
				(queryBuilder.getPage() != null ? " OFFSET " + offset + " " : "");
		// Perform desired database operations
        PreparedStatement preparedStatement = connection.prepareStatement(q);
       
        
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Map<String, Object>> list = convertToList(resultSet, fields, queryBuilder.getProject());
        // Close the connection
        closeConnection(resultSet, preparedStatement, connection);
        return list;
    }
    
    public int selectCountOperation (String entityName, QueryBuilder queryBuilder) throws SQLException, ClassNotFoundException{
        // Connect to the database
        Connection connection = getConnection();
		String q = "SELECT count(*) FROM " + entityName + queryBuilder.generateWhereClause() + (queryBuilder.getOrderby() != null ? " ORDER BY " + queryBuilder.getOrderby() + " " + queryBuilder.getOrderby() : "");
		// Perform desired database operations
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt(1); // Le résultat COUNT(*) sera dans la première colonne
        }
        // Close the connection
        closeConnection(resultSet, preparedStatement, connection);
        return count;
    }

	public Long insertOperation(String entityName, List<EntityField> fields, QueryBuilder queryBuilder) throws ClassNotFoundException, SQLException {
		// Connect to the database
        Connection connection = getConnection();
        Map<String, Object> data = queryBuilder.getData();
        String q = "INSERT INTO " + entityName + "(";
        // set key
        int i = 0;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
        	q = q + entry.getKey()  + (data.size() - 1 == i ? ")" : ", ");
        	i++;
		}
        q = q + " VALUES (";
        // set value
        i = 0;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
        	q = q + "'" + entry.getValue()  + (data.size() - 1 == i ? "')" : "', ");
        	i++;
		}
        // Perform desired database operations
        PreparedStatement preparedStatement = connection.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        // Récupérez les clés générées
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        Long generatedId = null;
        if (generatedKeys.next()) {
            generatedId = generatedKeys.getLong(1); // Assumant que l'ID est un long, utilisez getInt si c'est un int
        }
        closeConnection(generatedKeys, preparedStatement, connection);
		return generatedId;
	}
	
	public Long updateOperation(String entityName, List<EntityField> fields, Long id, QueryBuilder queryBuilder) throws ClassNotFoundException, SQLException {
		// Connect to the database
        Connection connection = getConnection();
        Map<String, Object> data = queryBuilder.getData();
        String q = "UPDATE " + entityName + " SET ";
        int i = 0;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
        	q = q + entry.getKey()  + " = '" + entry.getValue() + (data.size() - 1 == i ? "'" : "', ");
        	i++;
		}
        q = q + " WHERE id=" + id + ";"; 
        // Perform desired database operations
        PreparedStatement preparedStatement = connection.prepareStatement(q); 
        preparedStatement.executeUpdate();
        closeConnection(null, preparedStatement, connection);
		return id;
	}
	
	public void deleteOperation(String entityName, Long id) throws ClassNotFoundException, SQLException {
		// Connect to the database
        Connection connection = getConnection();
        String q = "DELETE FROM " + entityName + " WHERE id=" + id + ";"; 
        // Perform desired database operations
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.executeUpdate();
        closeConnection(null, preparedStatement, connection);
	}
    
    private List<Map<String, Object>> convertToList(ResultSet resultSet, List<EntityField> fields, List<String> project) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 0; i < fields.size(); i++) {
            	if (project == null || project.isEmpty()) {
            		String columnName = fields.get(i).getName();
                    Object columnValue = resultSet.getObject(columnName);
                    row.put(columnName, columnValue);
				} else {
					if (project.contains(fields.get(i).getName()) ) {
	            		String columnName = fields.get(i).getName();
	                    Object columnValue = resultSet.getObject(columnName);
	                    row.put(columnName, columnValue);
					}
				}
            }
            list.add(row);
        }
        return list;
    }
	
}

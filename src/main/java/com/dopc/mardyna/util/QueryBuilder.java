package com.dopc.mardyna.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

import com.dopc.mardyna.entity.EntityField;
import com.dopc.mardyna.helper.DynamicClassGenerator;

import jakarta.persistence.EntityManager;

public class QueryBuilder {

	public enum QUERY_DIR {
		ASC, DESC
	}

	private Map <String, Object> data;

	private List<MatchBuilder> matchs;

	private List<String> project;

	private Long page;

	private int limit;

	private String orderby;

	private QUERY_DIR orderdir;

	public QueryBuilder() {
		super();
		this.page = (long) 1;
		this.limit = (int) 10;
		this.project = new ArrayList<String>();
		this.orderdir = QUERY_DIR.ASC;
		this.matchs = new ArrayList<MatchBuilder>();
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public List<MatchBuilder> getMatchs() {
		return matchs;
	}

	public void setMatchs(List<MatchBuilder> matchs) {
		this.matchs = matchs;
	}
	
	public String getProjectListString() {
		String result = "";
        for (int i = 0; i < project.size(); i++) {
            result = result + project.get(i);
            if (i < project.size() - 1) {
                result = result + ", ";
            }
        }
		return result.toString();
	}

	public List<String> getProject() {
		return project;
	}

	public void setProject(List<String> project) {
		this.project = project;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public QUERY_DIR getOrderdir() {
		return orderdir;
	}

	public void setOrderdir(QUERY_DIR orderdir) {
		this.orderdir = orderdir;
	}

	public String generateWhereClause() {
		String where = "";

		if (matchs.size() > 0) {
			where = " WHERE ";
		}

		for (MatchBuilder match : matchs) {
			if (!where.equals(" WHERE ")) {
				 where = where + " AND ";
			}
			switch (match.getOperator()) {
			case IS:
				where = where + match.getName() + " = '" + match.getValue() + "' ";
				break;
			case LIKE:
				where = where + match.getName() + " LIKE '%" + match.getValue() + "%' ";
				break;
			case NE:
				where = where + match.getName() + " <> '" + match.getValue() + "' ";
				break;
			case IN:
				String ins = "";
				@SuppressWarnings("unchecked") List<String> elements = (List<String>) match.getValue();
				for (String element : elements) {
					ins = ins + (ins == "" ? "'" + element + "'" : ",'" + element + "'");
				}
				where = where + match.getName() + " IN (" + ins + ") ";
				break;
			case NIN:
				String nins = "";
				@SuppressWarnings("unchecked") List<String> nelements = (List<String>) match.getValue();
				for (String element : nelements) {
					nins = nins + (nins == "" ? "'" + element + "'" : ",'" + element + "'");
				}
				where = where + match.getName() + " NOT IN (" + nins + ") ";
				break;
			case GT:
				where = where + match.getName() + " > " + match.getValue() + " ";
				break;
			case GTE:
				where = where + match.getName() + " >= " + match.getValue() + " ";
				break;
			case LT:
				where = where + match.getName() + " < " + match.getValue() + " ";
				break;
			case LTE:
				where = where + match.getName() + " <= " + match.getValue() + " ";
				break;
			case WHERE:
				where = where + (String) match.getValue() + " ";
				break;
			default:
				break;
			}
		}

		return where;
	}

	public static List<HashMap<String, Object>> makeProjection(List<Object> objects, List<String> projections, Class<?> cl) throws IllegalArgumentException, IllegalAccessException{

		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String,Object>>();
		Field[] allFields = cl.getDeclaredFields();

		for (Object o : objects) {
			HashMap<String, Object> map = new HashMap<String,Object>();
			if (projections.size() == 0) {
				for (Field field : allFields) {
		    		field.setAccessible(true);
			        map.put(field.getName(), field.get(o));

			    }
			} else {
				for (String projection : projections) {
				    for (Field field : allFields) {
				    	if (field.getName().equals(projection)) {
				    		
				    		field.setAccessible(true);
					        map.put(field.getName(), field.get(o));
						}
				    }
				}
			}

			maps.add(map);
		}

		return maps;
	}

	@SuppressWarnings("unchecked")
	public static List<HashMap<String, Object>> QueryResult(QueryBuilder queryBuilder, String className, List<EntityField> fields, EntityManager entityManager) throws Exception {
		String q = "SELECT * FROM " + className + queryBuilder.generateWhereClause() + (queryBuilder.orderby != null ? " ORDER BY " + queryBuilder.orderby + " " + queryBuilder.orderdir : "");
		
		Class<?> dynamicClass = DynamicClassGenerator.generateClass(className, fields);
		return QueryBuilder.makeProjection(entityManager.createNativeQuery(q, dynamicClass)
				.setMaxResults(queryBuilder.getLimit())
				.setFirstResult((int) (queryBuilder.getPage() > 0 ? ( ( queryBuilder.getPage() - 1 ) * queryBuilder.getLimit() ) : 0)).getResultList() , queryBuilder.getProject(), dynamicClass);
	}
	
	@SuppressWarnings("unchecked")
	public static List<HashMap<String, Object>> QueryResult(QueryBuilder queryBuilder, String className, Class<?> _class, EntityManager entityManager) throws Exception {
		String q = "SELECT * FROM " + className + queryBuilder.generateWhereClause() + (queryBuilder.orderby != null ? " ORDER BY " + queryBuilder.orderby + " " + queryBuilder.orderdir : "");
		// System.out.print(q);
		return QueryBuilder.makeProjection(entityManager.createNativeQuery(q, _class)
				.setMaxResults(queryBuilder.getLimit())
				.setFirstResult((int) (queryBuilder.getPage() > 0 ? ( ( queryBuilder.getPage() - 1 ) * queryBuilder.getLimit() ) : 0)).getResultList() , queryBuilder.getProject(), _class);
	}

	public static BigDecimal countResult(QueryBuilder query, String className, EntityManager entityManager) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String q = "SELECT COUNT(*) FROM " + className + query.generateWhereClause();
		return (BigDecimal) entityManager.createNativeQuery(q).getSingleResult();
	}

}
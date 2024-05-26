package com.dopc.mardyna.util;

public class MatchBuilder {

	public enum MATCH_OPERATOR {
		IS, LIKE, NE, IN, NIN, GT, GTE, LT, LTE, WHERE
	}

	private MATCH_OPERATOR operator;

	private String name;

	private Object value;

	public MATCH_OPERATOR getOperator() {
		return operator;
	}

	public void setOperator(MATCH_OPERATOR operator) {
		this.operator = operator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
package com.dopc.mardyna.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ENTITY_FIELD")
public class EntityField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "maxlength", nullable = true)
    private Integer maxlength;
    
    @Column(name = "precision", nullable = true)
    private Integer precision;
    
    @Column(name = "scale", nullable = true)
    private Integer scale;
    
    @Column(name = "isnullable", nullable = true)
    private Boolean isnullable;
    
    @Column(name = "createdat", nullable = false)
    private Date createdAt;
    
    @Column(name = "updateddat", nullable = false)
    private Date updatedAt;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonBackReference
    private EntitySchema entitySchema;

	public EntityField() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public EntityField(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(Integer maxlength) {
		this.maxlength = maxlength;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public Boolean isIsnullable() {
		return isnullable;
	}

	public void setIsnullable(Boolean isnullable) {
		this.isnullable = isnullable;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public EntitySchema getEntitySchema() {
		return entitySchema;
	}

	public void setEntitySchema(EntitySchema entitySchema) {
		this.entitySchema = entitySchema;
	}
	
}

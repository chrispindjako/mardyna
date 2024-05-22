package com.dopc.mardyna.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    
    @Column(name = "label", nullable = true)
    private String label;
    
    @Column(name = "precision", nullable = true)
    private Integer precision;
    
    @Column(name = "sequence", nullable = true)
    private Integer sequence;
    
    @Column(name = "scale", nullable = true)
    private Integer scale;
    
    @Column(name = "isprimary", nullable = true)
    private boolean isprimary;
    
    @Column(name = "isnullable", nullable = true)
    private boolean isnullable;
    
    @Column(name = "createdat", nullable = false)
    private Date createdAt;
    
    @Column(name = "updateddat", nullable = false)
    private Date updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "entity_schema_id", nullable = false)
    private EntitySchema entitySchema;

	public EntityField() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public boolean isIsprimary() {
		return isprimary;
	}

	public void setIsprimary(boolean isprimary) {
		this.isprimary = isprimary;
	}

	public boolean isIsnullable() {
		return isnullable;
	}

	public void setIsnullable(boolean isnullable) {
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

package com.dopc.mardyna.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="SCREEN_FIELD")
public class ScreenField implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    
    @Column(name = "screen", nullable = false)
    private Long screen;
    
    @Column(name = "entity", nullable = true)
    private Long entity;
    
    @Column(name = "checklisttype", nullable = true)
    private String checklisttype;
    
    @Column(name = "checklistvalue", nullable = true)
    private String checklistvalue;

	public ScreenField() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public ScreenField(String name) {
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
	
	public Boolean getIsnullable() {
		return isnullable;
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

	public Long getEntity() {
		return entity;
	}

	public void setEntity(Long entity) {
		this.entity = entity;
	}

	public Long getScreen() {
		return screen;
	}

	public void setScreen(Long screen) {
		this.screen = screen;
	}

	public String getChecklisttype() {
		return checklisttype;
	}

	public void setChecklisttype(String checklisttype) {
		this.checklisttype = checklisttype;
	}

	public String getChecklistvalue() {
		return checklistvalue;
	}

	public void setChecklistvalue(String checklistvalue) {
		this.checklistvalue = checklistvalue;
	}
		
}

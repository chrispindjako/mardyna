package com.dopc.mardyna.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="SCREEN_FIELD_CHECK_LIST_VALUE")
public class ScreenFieldChecklistValue implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;
    
    @Column(name = "description", nullable = true)
    private String description;
    
    @Column(name = "field", nullable = false)
    private Long field;
    
    @Column(name = "screen", nullable = false)
    private Long screen;
    
    @Column(name = "entity", nullable = false)
    private Long entity;
    
    @Column(name = "createdat", nullable = false)
    private Date createdAt;
    
    @Column(name = "updateddat", nullable = false)
    private Date updatedAt;
    
    @Column(name = "notused", nullable = true)
    private Boolean notused;

	public ScreenFieldChecklistValue() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public ScreenFieldChecklistValue(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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


	public Long getScreen() {
		return screen;
	}

	public void setScreen(Long screen) {
		this.screen = screen;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getField() {
		return field;
	}

	public void setField(Long field) {
		this.field = field;
	}

	public Long getEntity() {
		return entity;
	}

	public void setEntity(Long entity) {
		this.entity = entity;
	}

	public Boolean getNotused() {
		return notused;
	}

	public void setNotused(Boolean notused) {
		this.notused = notused;
	}	
		
}

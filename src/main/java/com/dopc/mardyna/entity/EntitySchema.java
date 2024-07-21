package com.dopc.mardyna.entity;


import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="ENTITY_SCHEMA")
public class EntitySchema  implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "createdat", nullable = false)
    private Date createdAt;
    
    @Column(name = "updateddat", nullable = false)
    private Date updatedAt;
    
    @Column(name = "isgenerated", nullable = true)
    private Boolean isgenerated;

    @Column(name = "synchronize", nullable = true)
    private Boolean synchronize;
    
	/*
	 * @OneToMany(mappedBy = "entitySchema")
	 * 
	 * @JsonManagedReference private List<EntityField> fields;
	 * 
	 * @OneToMany(mappedBy = "entitySchema")
	 * 
	 * @JsonManagedReference private List<Screen> screens;
	 */

	public EntitySchema() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
		this.isgenerated = false;
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

	public Boolean isIsgenerated() {
		return isgenerated;
	}

	public void setIsgenerated(Boolean isgenerated) {
		this.isgenerated = isgenerated;
	}

	public Boolean getIsgenerated() {
		return isgenerated;
	}

	public Boolean getSynchronize() {
		return synchronize;
	}

	public void setSynchronize(Boolean synchronize) {
		this.synchronize = synchronize;
	}
	
	

//	public List<EntityField> getFields() {
//		return fields;
//	}
//
//	public void setFields(List<EntityField> fields) {
//		this.fields = fields;
//	}
//
//	public List<Screen> getScreens() {
//		return screens;
//	}
//
//	public void setScreens(List<Screen> screens) {
//		this.screens = screens;
//	}

	

    
}

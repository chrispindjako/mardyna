package com.dopc.mardyna.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="SCREEN")
public class Screen  implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "url", nullable = true)
    private String url;

    @Column(name = "entity", nullable = true)
    private Long entity;
    
    @Column(name = "createdat", nullable = false)
    private Date createdAt;
    
    @Column(name = "updateddat", nullable = false)
    private Date updatedAt;

    
//    @ManyToOne(fetch = FetchType.LAZY)
//    private EntitySchema entitySchema;

	public Screen() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
		// this.fields = new ArrayList<EntityField>();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	
	

//	public EntitySchema getEntitySchema() {
//		return entitySchema;
//	}
//
//	public void setEntitySchema(EntitySchema entitySchema) {
//		this.entitySchema = entitySchema;
//	}

    
}

package com.atguigu.crm.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public abstract class IdEntity implements Serializable {

	protected Long id;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRM_SEQ")
	@SequenceGenerator(name="CRM_SEQ", sequenceName="CRM_SEQ", allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}

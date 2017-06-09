package com.zz.model.admin;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Authority implements Serializable {

	private static final long serialVersionUID = -1820740990712863351L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToMany
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Authority() {
		super();
	}

}

package com.zz.model.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = -599023152590710068L;

	@Id
	@GeneratedValue
	private Long id;
	/** 创建日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	/** 修改日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifyDate;

	/** 名称 */
	@Column(nullable = false)
	private String name;

	/** 编码 */
	private String code;

	/** 是否内置 */
	private Boolean isSystem;

	/** 描述 */
	private String description;

	@ManyToMany(mappedBy = "roles")
	private List<Admin> admins;

	/** 权限资源 */
	@ManyToMany
	@JoinTable(name = "role_authority", joinColumns = { @JoinColumn(name = "authority_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Authority> authorities;

	@ManyToMany
	@JoinTable(name = "role_menu", joinColumns = { @JoinColumn(name = "menu_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Menu> menus;

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public Role() {
		super();
	}

}

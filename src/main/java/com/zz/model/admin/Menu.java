package com.zz.model.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Menu implements Serializable {

	private static final long serialVersionUID = 299544619736055006L;

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	@Id
	@GeneratedValue
	private Long id;

	/** 创建日期 */
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	/** 修改日期 */
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifyDate;

	/** 名称 */
	@Column(nullable = false)
	private String name;

	/** 全称 */
	@Column(nullable = false)
	private String fullName;

	/** 树路径 */
	private String treePath;

	/** 层级 */
	private Integer grade;

	/** 上级菜单ID */
	private Long parent;

	/** 排序 */
	private Integer orders;

	/** 菜单值 */
	@OneToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private MenuValue menuValue;

	@ManyToMany
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public MenuValue getMenuValue() {
		return menuValue;
	}

	public void setMenuValue(MenuValue menuValue) {
		this.menuValue = menuValue;
	}

	public static String getTreePathSeparator() {
		return TREE_PATH_SEPARATOR;
	}

	public Menu() {
		super();
	}

}

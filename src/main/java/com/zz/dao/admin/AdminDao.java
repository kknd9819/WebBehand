package com.zz.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zz.model.admin.Admin;

public interface AdminDao extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {

	/**
	 * 根据用户名查找
	 * 
	 * @param username
	 * @return
	 */
	List<Admin> findByUsername(String username);

}

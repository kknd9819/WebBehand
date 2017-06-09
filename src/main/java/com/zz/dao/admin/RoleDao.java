package com.zz.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zz.model.admin.Admin;
import com.zz.model.admin.Role;

/**
 * 角色持久层接口
 * 
 * @Date 2014-12-29
 * @author 欧志辉
 * @version 1.0
 */
public interface RoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

	@Query("select r from Role r where r.id in (:roles)")
	List<Role> findRoles(@Param("roles") Long[] roleId);
	
	List<Role> findByAdmins(List<Admin> admins);
}
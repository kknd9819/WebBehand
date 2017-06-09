package com.zz.service.admin;


import java.util.List;

import org.springframework.data.domain.Page;

import com.zz.model.admin.Role;
import com.zz.model.basic.Pageable;

/**
 * 角色服务层接口
 * @Date 2014-12-29
 * @author 欧志辉
 * @version 1.0
 */
public interface RoleService {
	/**
	 * 新增角色
	 * @param role 角色
	 * @param authorities 权限值数组
	 * @return Long 保存实体的主键
	 */
	 Long saveRole(Role role, String authorities);
	
	/**
	 * 新增角色
	 * @param role 角色
	 * @param authorities 权限值数组
	 */
	 void updateRole(Role role, String authorities);


	 List<Role> findAll();

	
	/**
	 * 分页查找所有的角色
	 * @param pageable
	 * @return Page<Role>
	 */
	 Page<Role> findPage(Pageable pageable);
	
	/**
	 * 根据管理员ID查询管理员的角色ID
	 * @param adminId
	 * @return Set<Long>
	 */
	 List<Long> findRoleIdsByAdminId(Long adminId);

	 List<Role> findRoleByAdminId(Long id);

     Role findOne(Long id);

	 void batchDelete(List<Role> roles);
}
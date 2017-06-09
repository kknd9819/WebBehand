package com.zz.dao.admin;



import com.zz.model.admin.MenuValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 菜单值持久层接口
 * @Date 2014-12-30
 * @author 欧志辉
 * @version 1.0
 */
public interface MenuValueDao extends JpaRepository<MenuValue,Long> {
	
	/**
	 * 根据菜单值的名称查找菜单值
	 * @param vName 菜单权限值(忽略大小写)
	 * @return 名称是否存在
	 */
	 @Query("select mv from MenuValue mv where mv.vName = ?1")
	 List<MenuValue> nameExists( String vName);
	

}
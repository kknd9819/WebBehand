package com.zz.service.admin;



import com.zz.model.admin.MenuValue;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 菜单权限值服务层接口
 * @Date 2014-12-29
 * @author 欧志辉
 * @version 1.0
 */
public interface MenuValueService  {
	
	/**
	 * 判断菜单权限值名称是否存在
	 * @param vName 菜单权限值(忽略大小写)
	 * @return 名称是否存在
	 */
	boolean nameExists( String vName);
	
	/**
	 * 分页查找所有的菜单权限值
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	 Page<MenuValue> findPage(int pageNo, int pageSize);
	
	/**
	 * 批量删除菜单权限值
	 * @param menuValues
	 */
	 void batchDelete(List<MenuValue> menuValues);

	 MenuValue save(MenuValue menuValue);

	 MenuValue findOne(Long valueId);

	 void delete(Long vid);

	 List<MenuValue> findAll();
}
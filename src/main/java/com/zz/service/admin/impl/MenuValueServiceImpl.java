package com.zz.service.admin.impl;


import com.zz.dao.admin.MenuValueDao;
import com.zz.model.admin.MenuValue;
import com.zz.service.admin.MenuValueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单权限值服务层接口实现
 * @Date 2014-12-29
 * @author 欧志辉
 * @version 1.0
 */
@Service("menuValueServiceImpl")
public class MenuValueServiceImpl implements MenuValueService {
	
	@Resource
	private MenuValueDao menuValueDao;

	@Override
	public boolean nameExists( String vName) {
		
		List<MenuValue> list = menuValueDao.nameExists( vName);
		return list != null && list.size() > 0;
	}

	@Override
	public Page<MenuValue> findPage(int pageNo, int pageSize) {
		PageRequest pageRequest = new PageRequest(pageNo,pageSize);
		return menuValueDao.findAll(pageRequest);
	}

	@Override
	public void batchDelete(List<MenuValue> menuValues) {
		menuValueDao.delete(menuValues);
	}

	@Override
	public MenuValue save(MenuValue menuValue) {
		 return menuValueDao.save(menuValue);
	}

	@Override
	public MenuValue findOne(Long valueId) {
		return menuValueDao.findOne(valueId);
	}

	@Override
	public void delete(Long vid) {
		menuValueDao.delete(vid);
	}

	@Override
	public List<MenuValue> findAll() {
		return menuValueDao.findAll();
	}


}
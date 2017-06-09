package com.zz.service.admin.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.zz.dao.admin.MenuDao;
import com.zz.dao.admin.RoleDao;
import com.zz.model.admin.Menu;
import com.zz.model.basic.Pageable;
import com.zz.service.admin.MenuService;
import com.zz.util.shengyuan.JsonUtils;

/**
 * 菜单服务层接口实现
 * @Date 2014-12-30
 * @author 欧志辉
 * @version 1.0
 */
@Service("menuServiceImpl")
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private MenuDao menuDao;

	@Resource
	private RoleDao roleDao;


	@Override
	public List<Menu> findRoots() {
		
		return menuDao.findRoots();
	}

	@Override
	public Page<Menu> findRootsForPage(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int pageNo = pageable.getPageNumber();
		return menuDao.findAll(new PageRequest(pageNo, pageSize));
	}

	@Override
	public List<Menu> findChildren(Long parentId) {
		return menuDao.findChildren(parentId);
	}

	@Override
	public List<Menu> findChildrenMenu(Long parentId) {
		return menuDao.findChildrenMenu(parentId);
	}
	
	@Override
	public Long saveMenu(Menu menu) {
		Assert.notNull(menu,"menu不能为NULL");
		setValue(menu);
		menu.setCreateDate(new Date());
		menu.setModifyDate(new Date());
		Menu save = menuDao.save(menu);
		return save.getId();
	}

	@Override
	public int updateMenu(Menu menu) {
		Assert.notNull(menu,"menu不能为NULL");
		setValue(menu);
		List<Menu> children = menuDao.findChildrenMenu(menu.getId());
		for (Menu child : children) {
			setValue(child);
			menuDao.save(child);
		}
		Menu source = menuDao.findOne(menu.getId());
		menu.setCreateDate(source.getCreateDate());
		menu.setModifyDate(new Date());
		menuDao.save(menu);
		return 1;
	}
	
	/**
	 * 设置值
	 * 
	 * @param menu 角色菜单
	 */
	private void setValue(Menu menu) {
		if (menu == null) {
			return;
		}
		Long parentId = menu.getParent();
		if (parentId != null) {
			Menu parent = menuDao.getOne(parentId);
			menu.setFullName(parent.getFullName() + menu.getName());
			menu.setTreePath(parent.getTreePath() + parentId + Menu.TREE_PATH_SEPARATOR);
		} else {
			menu.setTreePath(Menu.TREE_PATH_SEPARATOR);
		}
		menu.setGrade(menu.getTreePath().length());
	}

	@Override
	public List<Menu> findMenuByMenuValueId(Long menuValueId) {
		
		return menuDao.findMenuByMenuValueId(menuValueId);
	}

	@Override
	public String generateTree(Long roleId) {
		List<Menu> menuAll = menuDao.getAllMenuMenuValue();
		List<Menu> menus = menuDao.findByRoleId(roleId);
		Set<Long> hasMenus = new HashSet<Long>();
		if (menus != null) {
			for (Menu menu : menus) {
				hasMenus.add(menu.getId());
			}
		}
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		if (menuAll != null) {
			for (Menu menu : menuAll) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", menu.getId());
				map.put("name", menu.getName());
				map.put("pId", menu.getParent());
				map.put("authority", menu.getMenuValue().getvName());
				if(hasMenus.contains(menu.getId())){
					map.put("checked", true);
				}
				jsonList.add(map);
			}
		}
		return JsonUtils.toJson(jsonList);
	}

	@Override
	public String genereateMenuTree(Long parentId) {
		List<Menu> menus = menuDao.findAll();
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		if (menus != null) {
			for (Menu menu : menus) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", menu.getId());
				map.put("name", menu.getName());
				map.put("pId", menu.getParent());
				if (parentId != null) {
					if(menu.getId().longValue() == parentId.longValue()) {
						map.put("checked", true);
					}
				}
				jsonList.add(map);
			}
		}
		return JsonUtils.toJson(jsonList);
	}

	@Override
	public Menu findOne(Long parentId) {
		return menuDao.findOne(parentId);
	}

	@Override
	public void delete(Long id) {
		 menuDao.delete(id);
	}
}
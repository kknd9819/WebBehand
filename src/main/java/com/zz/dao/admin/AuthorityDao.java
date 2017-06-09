package com.zz.dao.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zz.model.admin.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Long> {
	
	Authority findByName(String name);

}

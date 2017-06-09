package com.zz.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zz.dao.admin.AdminDao;
import com.zz.model.admin.Admin;



@Component
public class CustomUserService implements UserDetailsService{

    @Autowired
    private AdminDao adminDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Admin> admin = adminDao.findByUsername(username);
        if(admin == null || admin.size() == 0){
            throw new UsernameNotFoundException("用户名不存在");
        }

        return new SecurityUser(admin.get(0));
    }



}

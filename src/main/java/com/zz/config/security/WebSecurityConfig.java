package com.zz.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserService();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}

	@Bean
	public CsrfSecurityRequestMatcher requireCsrfProtectionMatcher() {
		CsrfSecurityRequestMatcher csrf = new CsrfSecurityRequestMatcher();
		List<String> list = new ArrayList<String>();
		list.add("/rest/");
		csrf.setExecludeUrls(list);
		return csrf;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler())
        .and().logout().invalidateHttpSession(true).logoutSuccessUrl("/logout").permitAll()
        .and().exceptionHandling().accessDeniedPage("/deny")
        .and().authorizeRequests()
        .antMatchers("/resources/**","/admin/common/**").permitAll()
        .anyRequest().authenticated()
        .and().csrf().requireCsrfProtectionMatcher(requireCsrfProtectionMatcher())
        .and().sessionManagement().maximumSessions(1);
        
      
    }

}

package com.zz.config.security;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 排除CSRF保护策略
 * 
 * @author 郑茁
 * @version 1.0
 * @date 2017/06/07
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {

	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

	private List<String> execludeUrls;

	@Override
	public boolean matches(HttpServletRequest request) {
		if (execludeUrls != null && execludeUrls.size() > 0) {
			String servletPath = request.getServletPath();
			for (String url : execludeUrls) {
				if (servletPath.contains(url)) {
					return false;
				}
			}
		}
		return !allowedMethods.matcher(request.getMethod()).matches();
	}

	public List<String> getExecludeUrls() {
		return execludeUrls;
	}

	public void setExecludeUrls(List<String> execludeUrls) {
		this.execludeUrls = execludeUrls;
	}

}

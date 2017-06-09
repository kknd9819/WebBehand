package com.zz.config.template.method;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.zz.util.shengyuan.ResourceUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 模板方法 - 多语言
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Component("messageMethod")
public class MessageMethod implements TemplateMethodModelEx {

	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null
				&& StringUtils.isNotEmpty(arguments.get(0).toString())) {
			String message = null;
			String code = arguments.get(0).toString();
			if (arguments.size() > 1) {
				Object[] args = arguments.subList(1, arguments.size()).toArray();
				message = ResourceUtils.getString("message", code, args);
			} else {
				message = ResourceUtils.getString("message", code);
			}
			return new SimpleScalar(message);
		}
		return null;
	}

}
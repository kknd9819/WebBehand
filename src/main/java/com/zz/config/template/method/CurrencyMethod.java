package com.zz.config.template.method;


import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.zz.util.shengyuan.Setting;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 模板方法 - 货币格式化
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Component("currencyMethod")
public class CurrencyMethod implements TemplateMethodModelEx {

	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString())) {
			boolean showSign = false;
			boolean showUnit = false;
			if (arguments.size() == 2) {
				if (arguments.get(1) != null) {
					showSign = ((TemplateBooleanModel) arguments.get(1)).getAsBoolean();
				}
			} else if (arguments.size() > 2) {
				if (arguments.get(1) != null) {
					showSign = ((TemplateBooleanModel) arguments.get(1)).getAsBoolean();
				}
				if (arguments.get(2) != null) {
					showUnit = ((TemplateBooleanModel) arguments.get(2)).getAsBoolean();
				}
			}
			BigDecimal amount = new BigDecimal(arguments.get(0).toString());
			String price = amount.setScale(Setting.getPriceScale(), BigDecimal.ROUND_HALF_UP).toString();
			if (showSign) {
				String currencySign = Setting.getCurrencySign();
				price = currencySign + price;
			}
			if (showUnit) {
				String currencyUnit = Setting.getCurrencyUnit();
				price += currencyUnit;
			}
			return new SimpleScalar(price);
		}
		return null;
	}
}
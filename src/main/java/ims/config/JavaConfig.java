package ims.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
@Configuration
public class JavaConfig {
	/**
	 * Specify messages properties file and character code.
	 *
	 * @return Message source bean
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * overwrite validation message on message source.
	 *
	 * @return Bean of LocalValidatorFactoryBean class
	 */
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}

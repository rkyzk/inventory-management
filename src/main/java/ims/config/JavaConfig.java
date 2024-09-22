package ims.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * バリデーションメッセージを管理するためのクラス.
 * @author R.Yazaki
 * @version 1.0.0
 */
@Configuration
public class JavaConfig {
	/**
	 * メッセージのプロパティファイル名と文字コードを指定するメソッド.
	 *
	 * @return メッセージソースのBean
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * バリデーションメッセージをメッセージソースに上書きする為のメソッド.
	 *
	 * @return LocalValidatorFactoryBeanクラスのBean
	 */
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}

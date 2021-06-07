package config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configurable
public class InternalicionalizacaoConfig {
	 @Bean
	    public MessageSource messageSource(){
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasename("classpath:messages");
	        messageSource.setDefaultEncoding("ISO-8859-1");
	        messageSource.setDefaultLocale(Locale.getDefault());
	        return messageSource;
	    }

	    @Bean
	    public LocalValidatorFactoryBean validatorFactoryBean(){
	        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	        bean.setValidationMessageSource(messageSource());
	        return bean;
	    }
}

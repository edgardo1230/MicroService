package com.nearshoretechnology.focalpoint.api.admin.config;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nearshoretechnology.focalpoint.common.async.AsyncConfig;
import com.nearshoretechnology.focalpoint.common.cache.CacheConfig;
import com.nearshoretechnology.focalpoint.common.dao.DaoConfig;
import com.nearshoretechnology.focalpoint.common.security.SecurityConfig;
import com.nearshoretechnology.focalpoint.common.sse.SseConfig;

@Configuration
@Import({AsyncConfig.class, SseConfig.class, DaoConfig.class, SecurityConfig.class, CacheConfig.class})
public class AppConfig {

	@Bean
	public ErrorAttributes errorAttributes() {
	    
		return new DefaultErrorAttributes(){
	    	@Override
	        public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
	            Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
	            errorAttributes.remove("exception");
	            return errorAttributes;
	        }
	    };
	    
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**")
            	.allowedMethods("*");
            }
        };
	}
	
	@Bean
    public CacheManager cacheManager() {
        return CacheConfig.createCacheManager("OfficeRespositoryImpl");
    }
	
	
	
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

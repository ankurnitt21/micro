package com.example.UserService.config;

import com.example.UserService.filter.SessionCheckFilter;
import com.example.UserService.interceptor.CustomInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final CustomInterceptor customInterceptor;

    public WebConfig(CustomInterceptor customInterceptor) {
        this.customInterceptor = customInterceptor;
    }

    // Register the filter and set it to run first
    @Bean
    public FilterRegistrationBean<SessionCheckFilter> sessionCheckFilter(RedisTemplate<String, String> redisTemplate) {
        SessionCheckFilter filter = new SessionCheckFilter(redisTemplate); // Pass the redisTemplate
        FilterRegistrationBean<SessionCheckFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/login", "/products", "/otherUrlPattern/*"); // Define your URL patterns
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); // Ensure it runs first
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor)
                .addPathPatterns("/login"); // Apply to specific endpoints
    }
}

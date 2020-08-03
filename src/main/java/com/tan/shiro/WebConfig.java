package com.tan.shiro;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/***
 * 配置WebMvc信息
 * @author jzze
 * @date
 */

@Configuration
@EnableSwagger2
//@EnableRedisRepositories
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
//        super.addCorsMappings(registry);
        //添加请求跨域
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将swagger-ui资源文件添加到静态资源库
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        /**
         * 配置静态访问资源
         * @param registry
         */
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}

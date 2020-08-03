package com.tan.shiro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {

    //配置了swagger的docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tan.controller"))
                .build();
    }

    @Bean
    public ApiInfo apiInfo(){
        Contact contact = new Contact("谭志勇", "http://www.tanzhiyong97.cn/", "tanzhiyong97@gmail.com");
        return new ApiInfo(
                "谭志勇的 Api Documentation",
                "再小的帆也能远航 Api Documentation",
                "1.0",
                "urn:tos",
                 contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                 new ArrayList());
    }
}

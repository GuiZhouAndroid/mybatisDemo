package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * created by on 2021/10/3
 * 描述：Swagger接口文档配置类+添加摘要信息
 *
 * @author ZSAndroid
 * @create 2021-10-3-11:08
 */
@Configuration //标记配置类
@EnableSwagger2 //是springfox提供的一个注解，代表swagger2相关技术开启,会扫描当前类所在包，及子包中所有的类型中的注解。做swagger文档的定值。
public class Swagger2Config {
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example")) //这里必须与包名一致，否则报错
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title("API文档标题")
                .description("API文档说明概述")
                .termsOfServiceUrl("https://blog.csdn.net/qq_39038178")
                .contact(new Contact("CSDN博客主页", "https://blog.csdn.net/qq_39038178", null))
                .build();
    }
}

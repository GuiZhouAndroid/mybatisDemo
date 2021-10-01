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
 * created by on 2021/9/21
 * 描述：Swagger接口文档配置类+添加摘要信息
 *
 * @author ZSAndroid
 * @create 2021-09-21-11:08
 */
@Configuration //标记配置类
@EnableSwagger2 //是springfox提供的一个注解，代表swagger2相关技术开启,会扫描当前类所在包，及子包中所有的类型中的注解。做swagger文档的定值。
public class Swagger2Config {
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title("张松毕设校园帮APP接口说明文档")
                .description("学院：六盘水师范学院，系别：数学与计算机科学学院，专业：计算机科学与技术，班级：计科3班。毕设题目：基于Android移动端+SpringBoot服务端的校园帮APP设计与实现。注明：本文API接口说明文档仅供本人开发者使用，若有侵权，及时联系。")
                .termsOfServiceUrl("https://blog.csdn.net/qq_39038178")
                .contact(new Contact("张松CSDN博客主页", "https://blog.csdn.net/qq_39038178", null))
                .build();
    }
}

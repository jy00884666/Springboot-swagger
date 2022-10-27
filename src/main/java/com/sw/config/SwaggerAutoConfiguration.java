package com.sw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {
    
    /*组名称*/
    @Value("${swagger.groupName1}")
    private String groupName1;
    
    @Value("${swagger.groupName2}")
    private String groupName2;
    
    /*api包扫描*/
    @Value("${swagger.basePackage1}")
    private String basePackage1;
    
    @Value("${swagger.basePackage2}")
    private String basePackage2;
    
    /*页面标题*/
    @Value("${swagger.title}")
    private String title;
    
    /*创建作者信息,名称*/
    @Value("${swagger.contactName}")
    private String contactName;
    
    /*创建作者信息,地址*/
    @Value("${swagger.contactUrl}")
    private String contactUrl;
    
    /*创建作者信息,Email*/
    @Value("${swagger.contactEmail}")
    private String contactEmail;
    
    /*版本号*/
    @Value("${swagger.version}")
    private String version;
    
    /*备注描述*/
    @Value("${swagger.desc}")
    private String desc;
    
    @Bean
    public Docket createRestApi1() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).groupName(groupName1)
                //写入自己的响应消息
                .globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                .globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, customerResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, customerResponseMessage())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage(basePackage1))
                .build();
        return docket;
    }
    
    @Bean
    public Docket createRestApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).groupName(groupName2)
                //写入自己的响应消息
                .globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                .globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, customerResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, customerResponseMessage())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage(basePackage2))
                .build();
        return docket;
    }
    
    //构建 api文档的详细信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title(title)
                //创建人 信息
                .contact(new Contact(contactName, contactUrl, contactEmail))
                //版本号
                .version(version)
                //描述
                .description(desc)
                .build();
    }
    
    /*设置自己的响应消息*/
    private List<ResponseMessage> customerResponseMessage() {
        List<ResponseMessage> list = new ArrayList<>();
        list.add(new ResponseMessageBuilder().code(200).message("请求成功").build());
        list.add(new ResponseMessageBuilder().code(201).message("资源创建成功").build());
        list.add(new ResponseMessageBuilder().code(204).message("服务器成功处理了请求，但不需要返回任何实体内容").build());
        list.add(new ResponseMessageBuilder().code(400).message("请求失败,具体查看返回业务状态码与对应消息").build());
        list.add(new ResponseMessageBuilder().code(401).message("请求失败,未经过身份认证").build());
        list.add(new ResponseMessageBuilder().code(403).message("请求失败,被禁止").build());
        list.add(new ResponseMessageBuilder().code(405).message("请求方法不支持").build());
        list.add(new ResponseMessageBuilder().code(415).message("请求媒体类型不支持").build());
        list.add(new ResponseMessageBuilder().code(500).message("服务器遇到了一个未曾预料的状况,导致了它无法完成对请求的处理").build());
        list.add(new ResponseMessageBuilder().code(503).message("服务器当前无法处理请求,这个状况是临时的，并且将在一段时间以后恢复").build());
        return list;
    }
    
}

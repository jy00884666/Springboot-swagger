package com.sw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
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

// 配置类
@Configuration
// 开启 swagger2 的自动配置
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
    
    /*跳转连接*/
    @Value("${swagger.termsOfServiceUrl}")
    private String termsOfServiceUrl;
    
    /*许可证*/
    @Value("${swagger.license}")
    private String license;
    
    /*许可证URL*/
    @Value("${swagger.licenseUrl}")
    private String licenseUrl;
    
    /*用户组*/
    @Bean
    /**
     * spring应用上下文，类中持有environment和BeanFactory接口等实现类的引用，也可以理解为在applicationContext皮衣下包装了各种顶级接口实现类。
     * Environment: spring应用的运行环境，保存了当前操作系统，JVM，被激活的application.properties文件的配置信息
     * 在Spring中当我们想拿到配置文件(不管是yml格式还是.properties格式)中的配置信息时，有很多种方式，采用Environment去获取是其中一种，优势是：
     * 可以通过getProperty这种比较通用的api来根据key获取value。
     * 当存在多份配置文件(比如SpringBoot应用jar包中有application.yml文件，外部也有application.yml文件)，能取到生效的配置文件信息。
     */
    public Docket createRestApi1(Environment environment) {
        // 设置环境范围
        Profiles profiles = Profiles.of("local", "dev", "test");
        // 如果在该环境返回内则返回：true，反之返回 false
        boolean flag = environment.acceptsProfiles(profiles);
        
        // 创建一个 swagger 的 bean 实例
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                // 配置基本信息
                .apiInfo(apiInfo()).groupName(groupName1)
                // 写入自己的响应消息
                .globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                .globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, customerResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, customerResponseMessage())
                // 设置扫描接口
                .select()
                /**apis 配置如何扫描接口
                 * RequestHandlerSelectors.any() // 扫描全部的接口，默认
                 * RequestHandlerSelectors.none() // 全部不扫描
                 * RequestHandlerSelectors.basePackage("com.sw...") // 扫描指定包下的接口，最为常用
                 * RequestHandlerSelectors.withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                 * RequestHandlerSelectors.withMethodAnnotation(PostMapping.class) // 扫描带有指定注解的方法接口
                 * */
                .apis(RequestHandlerSelectors.basePackage(basePackage1))
                /**paths 过滤器
                 * PathSelectors.any() // 满足条件的路径，该断言总为true
                 * PathSelectors.none() // 不满足条件的路径，该断言总为false（可用于生成环境屏蔽 swagger）
                 * PathSelectors.ant("/user/get**") // 满足字符串表达式路径
                 * PathSelectors.regex("/user/get.*") // 符合正则的路径
                 *
                 * // 只显示user路径下的页面
                 * Predicates.and(PathSelectors.regex("/user/.*"))
                 * // 过滤掉user路径下的所有页面
                 * Predicates.not(PathSelectors.regex("/user/.*"))
                 *
                 * //接口文档将只会展示 /user/get 和 /user/save 两个接口。
                 * Predicates.or(PathSelectors.ant("/user/get"),
                 *               PathSelectors.ant("/user/save"))
                 */
                .paths(PathSelectors.any())
                .build()
                // 是否开启 swagger：true -> 开启，false -> 关闭
                .enable(flag);
        return docket;
    }
    
    /*菜单组*/
    @Bean
    public Docket createRestApi2(Environment environment) {
        // 设置环境范围
        Profiles profiles = Profiles.of("local", "dev", "test");
        // 如果在该环境返回内则返回：true，反之返回 false
        boolean flag = environment.acceptsProfiles(profiles);
        
        // 创建一个 swagger 的 bean 实例
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                // 配置基本信息
                .apiInfo(apiInfo()).groupName(groupName2)
                // 写入自己的响应消息
                .globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                .globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, customerResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, customerResponseMessage())
                // 设置扫描接口
                .select()
                /** 配置如何扫描接口
                 * RequestHandlerSelectors.any() // 扫描全部的接口，默认
                 * RequestHandlerSelectors.none() // 全部不扫描
                 * RequestHandlerSelectors.basePackage("com.sw...") // 扫描指定包下的接口，最为常用
                 * RequestHandlerSelectors.withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                 * RequestHandlerSelectors.withMethodAnnotation(PostMapping.class) // 扫描带有指定注解的方法接口
                 * */
                .apis(RequestHandlerSelectors.basePackage(basePackage2))
                .build()
                // 是否开启 swagger：true -> 开启，false -> 关闭
                .enable(flag);
        return docket;
    }
    
    /*构建 api文档的详细信息*/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title(title)
                // 描述
                .description(desc)
                // 创建人作者信息,姓名,网址,邮箱
                .contact(new Contact(contactName, contactUrl, contactEmail))
                // 版本号
                .version(version)
                // 跳转连接
                .termsOfServiceUrl(termsOfServiceUrl)
                // 许可证
                .license(license)
                // 许可证URL
                .licenseUrl(licenseUrl)
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
        list.add(new ResponseMessageBuilder().code(404).message("找不到资源").build());
        list.add(new ResponseMessageBuilder().code(405).message("请求方法不支持").build());
        list.add(new ResponseMessageBuilder().code(409).message("业务逻辑异常").build());
        list.add(new ResponseMessageBuilder().code(415).message("请求媒体类型不支持").build());
        list.add(new ResponseMessageBuilder().code(500).message("服务器遇到了一个未曾预料的状况,导致了它无法完成对请求的处理").build());
        list.add(new ResponseMessageBuilder().code(502).message("nginx异常").build());
        list.add(new ResponseMessageBuilder().code(503).message("服务器当前无法处理请求,这个状况是临时的，并且将在一段时间以后恢复").build());
        return list;
    }
    
}

package com.sw.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
/**
 * @ApiModel 该注解是作用于类上面的，是用来描述类的一些基本信息的。
 * value：提供类的一个备用名，如果不设置，默认情况下将使用 class 类的名称
 * description：对于类，提供一个详细的描述信息
 * parent：这个属性用于描述的是类的一些父类信息
 * discriminator：这个属性解释起来比较麻烦，因为这个类主要体现在断言当中
 * subTypes：可以通过这个属性，指定我们想要使用的子类
 * */
@ApiModel(value = "用户实体", description = "用户实体")
public class User {
    
    /**
     * @ApiModelProperty 它的作用是添加和操作属性模块的数据。
     * value:参数类型为String，作用为此属性的简要描述。
     * name:参数类型为String，作用为允许重写属性的名称
     * allowableValues:参数类型为String，作用为允许此参数存储的长度。
     * access:参数类型为String，作用为允许从API文档中过滤属性
     * notes:参数类型为String，作用为该字段的注释说明
     * dataType:参数类型为String，作用为参数的数据类型。
     * required:参数类型为boolean，作用为指定参数是否可以为空，默认为false
     * position:参数类型为int，作用为允许显式地对模型中的属性排序。
     * hidden:参数类型为boolean，作用为是否允许模型属性隐藏在Swagger模型定义中，默认为false。
     * example:参数为String类型，作用为属性的示例值。
     * readOnly:参数类型为boolean，作用为是否允许将属性指定为只读，默认为false。
     * reference:参数类型为String，作用为指定对对应类型定义的引用，重写指定的任何其他数据名称。
     * allowEmptyValue:参数类型为boolean，作用为是否允许传递空值，默认为false
     */
    
    @ApiModelProperty(value = "主键")
    private int id;
    
    @ApiModelProperty(value = "姓名")
    private String name;
    
    @ApiModelProperty(value = "年龄")
    private int age;
    
    @ApiModelProperty(value = "地址")
    private String address;
    
}


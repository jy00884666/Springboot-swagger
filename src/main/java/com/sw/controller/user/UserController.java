package com.sw.controller.user;

import com.sw.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器")
public class UserController {
    
    /**Swagger 会将接口请求或者相应的实体类信息展示在 Models 下*/
    /**
     * @ApiOperation 该注解用来对某个方法/接口进行描述
     * value：对该操作进行简单的描述，尽量控制在120字符以内。
     * notes：对操作的详细描述。
     * httpMethod：指定操作使用的HTTP方法类型，可选值 “GET”、“HEAD”、“POST”、“PUT”、“DELETE”、“OPTIONS”和“PATCH”。
     * tags：用来给操作打标签，Swagger UI 将在操作列表下面展示 tag 列表，每个 tag 下面展示拥有该 tag 的操作列表。
     */
    
    /**
     * @RequestBody 作用：把数据写入请求的body中，绑定到 controller中方法的参数上，使用系统默认配置的HttpMessageConverter进行解析，
     * 然后把相应的数据绑定到要返回的对象上,再把HttpMessageConverter返回的对象数据绑定到 controller中方法的参数上。
     */
    
    @GetMapping("/getUsers")
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户信息")
    public List<User> getAllUsers(@RequestBody User request) {
        User user = new User();
        user.setId(100);
        user.setName("evan");
        user.setAge(20);
        user.setAddress("hl");
        List<User> list = new ArrayList<>();
        list.add(user);
        return list;
    }
    
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增用户", notes = "新增用户信息")
    public String save(@RequestBody User user) {
        return "OK";
    }
    
    @PutMapping("/update")
    @ApiOperation(value = "修改用户", notes = "修改用户信息")
    public User update(@RequestBody User user) {
        User response = user;
        response.setId(1);
        response.setName("测试返回1");
        response.setAddress("111");
        response.setAge(29);
        return response;
    }
    
    /**
     * @ApiParam 该注解使用在方法上或者参数上，字段说明，表示对参数的添加元数据（说明或者是否必填等）
     * 相关属性：
     * name：参数名
     * value：参数说明
     * defaultValue:定义参数默认值
     * allowableValues:定义参数取值范围
     * required：是否必填
     * access():定义参数访问规则
     * allowMultiple():定义参数能否接收多个数值
     * example():定义参数单个示例
     * hidden():定义参数显隐
     */
    
    /**
     * @RequestParam 是获取前端传递给后端的参数，可以是get方式，也可以是post方式。
     * 其中如果前端传递的参数和后端你接受的参数起的名字字段是一致的可以省略不写，
     * 也可以直接写@RequestParam String title,如果不一致一定要完整写，不然获取不到。
     */
    
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户", notes = "删除用户信息")
    public String delete(
            /**结合@RequestParam使用,如果不加example刷新页面会报错NumberFormatException烦人的很,原因是swagger版本问题*/
            @ApiParam(value = "用户主键", required = true, example = "1") @RequestParam(required = true) Integer id) {
        return "OK";
    }
    
    /**
     * @ApiImplicitParams 是Swagger的注解。这个注解用在控制器的方法上，用于说明方法的一组请求参数。
     * 配合@ApiImplicitParam注解来描述单个请求参数
     * @ApiImplicitParam name：参数名
     * value：参数解释
     * required：参数是否必须
     * dataType：参数类型
     * paramType：以下解释参数
     * *header：请求头
     * *query：?param=value的形式
     * *path：路径，Restful风格接口
     * *body：请求体
     * *form：以form表单的形式提交
     */
    
    /**
     * @PathVariable 是获取get方式，url后面参数，进行参数绑定,需要结合{pageNum}一起使用
     */
    
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码",
                    required = true, type = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",
                    required = true, type = "Integer"),
    })
    @ApiOperation(value = "分页查询用户信息")
    @GetMapping(value = "page/{pageNum}/{pageSize}")
    public String findByPage(@PathVariable Integer pageNum,
                             @PathVariable Integer pageSize) {
        return "OK";
    }
    
}


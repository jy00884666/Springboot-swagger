package com.sw.controller.menu;

import com.sw.entity.Menu;
import com.sw.zenum.MenuTypeEnum;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
@Api(tags = "菜单控制器")
public class MenuController {
    
    @GetMapping("/getMenus")
    @ApiOperation(value = "查询所有菜单", notes = "查询所有菜单信息")
    public List<Menu> getMenus() {
        Menu menu = new Menu();
        menu.setId(100);
        menu.setName("evan");
        List<Menu> list = new ArrayList<>();
        list.add(menu);
        return list;
    }
    
    @PostMapping("/save")
    @ApiOperation(value = "新增菜单", notes = "新增菜单信息")
    public String save(@RequestBody Menu menu) {
        return "OK";
    }
    
    @PutMapping("/update")
    @ApiOperation(value = "修改菜单", notes = "修改菜单信息")
    public String update(@RequestBody Menu menu) {
        return "OK";
    }
    
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除菜单", notes = "删除菜单信息")
    public String delete(int id) {
        return "OK";
    }
    
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码",
                    required = true, type = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",
                    required = true, type = "Integer"),
    })
    @ApiOperation(value = "分页查询菜单信息")
    @GetMapping(value = "page/{pageNum}/{pageSize}")
    public String findByPage(@PathVariable Integer pageNum,
                             @PathVariable Integer pageSize) {
        return "OK";
    }
    
    /**
     * swagger 2.6.0 版本以上 对于高版本（>2.6.0）的 swagger 来说，不需要特别的配置就能支持下拉框:
     * 下拉框的默认值是空，选择以后就能对 API 进行测试。
     * 此时，如果下拉框不选值（默认为空），也可以执行请求，因此需要在后台判空处理。
     */
    @ApiOperation(value = "类型查询菜单信息")
    @PostMapping("/queryMenuByType")
    public List<Menu> queryMenuByType(MenuTypeEnum menuTypeEnum) {
        Menu menu = new Menu();
        menu.setId(Integer.valueOf(menuTypeEnum.getCode()));
        menu.setName(menuTypeEnum.getName());
        List<Menu> list = new ArrayList<>();
        list.add(menu);
        return list;
    }
}


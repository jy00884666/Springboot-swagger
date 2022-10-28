package com.sw.zenum;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum MenuTypeEnum {
    
    TYPE_A1001("1001", "一级菜单"),
    TYPE_A2001("2001", "二级菜单"),
    
    ;
    
    @Setter
    private String code;
    
    @Setter
    private String name;
    
    MenuTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public static MenuTypeEnum getMenuTypeEnum(String code) {
        for (MenuTypeEnum key : MenuTypeEnum.values()) {
            if (key.code.equals(code)) {
                return key;
            }
        }
        return null;
    }
    
    public static String getMenuTypeByCode(String code) {
        for (MenuTypeEnum key : MenuTypeEnum.values()) {
            if (key.code.equals(code)) {
                return key.code;
            }
        }
        return null;
    }
    
}

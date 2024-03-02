package com.wusuowei.shiro_jwt.utils;

public enum RespEnum {


    UNAUTHENTICATEDERROR(401, "你没有认证"),
    UNAUTHORIZEDEDERROR(401, "你没有权限访问"),
    AUTHENTICATIONERROR(401,"身份验证失败")

    ;

    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    RespEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

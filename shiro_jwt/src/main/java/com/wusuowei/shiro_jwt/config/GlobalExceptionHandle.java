package com.wusuowei.shiro_jwt.config;


import com.wusuowei.shiro_jwt.utils.R;
import com.wusuowei.shiro_jwt.utils.RespEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

//捕获全局异常的处理
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public R handle401(ShiroException e) {
        return R.error(4001, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public R handleToken(ShiroException e) {
        return R.error(RespEnum.AUTHENTICATIONERROR);
    }

    // 捕捉shiro没有权限的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public R handle401(UnauthorizedException e) {
        System.out.println(e.getMessage());
        return R.error(RespEnum.UNAUTHORIZEDEDERROR);
    }
    // 捕捉shiro没有认证的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    public R handle401(UnauthenticatedException e) {
        System.out.println(e.getMessage());
        return R.error(RespEnum.UNAUTHENTICATEDERROR);
    }

    /**
     * @Validated 校验错误异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handler(MethodArgumentNotValidException e) throws IOException {
//        log.error("运行时异常:-------------->",e);
        BindingResult bindingR = e.getBindingResult();
        //这一步是把异常的信息最简化
        ObjectError objectError = bindingR.getAllErrors().stream().findFirst().get();
        return R.error(HttpStatus.BAD_REQUEST.value(),objectError.getDefaultMessage());
    }

    //运行时错误处理
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public R handle(RuntimeException e){

        return R.error(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = TokenExpiredException.class)
//    public R handler(TokenExpiredException e) throws IOException {
//        return R.error(HttpStatus.BAD_REQUEST.value(),"token已经过期，请重新登录",null);
//    }
}

package com.wusuowei.shiro_jwt.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wusuowei.shiro_jwt.model.po.Menu;
import com.wusuowei.shiro_jwt.model.po.User;
import com.wusuowei.shiro_jwt.model.po.UserRole;
import com.wusuowei.shiro_jwt.model.vo.UserVo;
import com.wusuowei.shiro_jwt.service.MenuService;
import com.wusuowei.shiro_jwt.service.UserRoleService;
import com.wusuowei.shiro_jwt.service.UserService;
import com.wusuowei.shiro_jwt.utils.JWTUtil;
import com.wusuowei.shiro_jwt.utils.R;
import com.wusuowei.shiro_jwt.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class ShiroUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "测试成功";
    }

    @PostMapping("/login")
    public R login(@RequestBody User user) throws UnsupportedEncodingException {
        log.info("/login方法被执行...");
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        User dbUser = userService.getUserByPass(user.getUsername(), md5Password);
        if(dbUser==null){
            return R.error("用户名或密码错误");
        }
        String token = JWTUtil.createToken(dbUser);
        List<Menu> menus = menuService.getUserMenus(dbUser.getId());
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("user",dbUser);
        map.put("menus",menus);
        return R.ok().setData(map);
    }

    @PostMapping("/register")
    public R register(@RequestBody UserVo userVo) throws UnsupportedEncodingException {
        String md5Password = DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes());
        User user = new User();
        user.setUsername(userVo.getUsername());
        user.setPassword(md5Password);
        userService.save(user);

        //设置默认角色
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId().toString());
        userRole.setRoleId("2");
        userRoleService.save(userRole);

        return R.ok();
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public R logout() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        redisUtil.hdel("refreshToken",user.getId().toString());
        redisUtil.hdel("userInfo",user.getId().toString());
        redisUtil.hdel("userPower","permission:"+user.getId());
        redisUtil.hdel("userPower","roles:"+user.getId());

        System.out.println(user);
        return R.ok();

    }

    /**
     * @description 检查用户名
     * @param username 用户名
     * @return {@link R }
     * @author LGY
     * @date 2023/03/24 21:09
     */
    @GetMapping("/check")
    public R countByUsername(@RequestParam String username) {
        long count = userService.count(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (count != 0) {
            return R.error("用户名已存在");
        }
        return R.ok();
    }

    //在JWTFilter中认证出错会跳转到这统一进行返回
    @RequestMapping(path = "/unauthorized/{message}")
    public R unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return R.error(4001,message);
    }
}

package com.wusuowei.shiro_jwt.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.wusuowei.shiro_jwt.model.po.User;
import com.wusuowei.shiro_jwt.service.MenuService;
import com.wusuowei.shiro_jwt.service.RoleService;
import com.wusuowei.shiro_jwt.service.UserService;
import com.wusuowei.shiro_jwt.utils.R;
import com.wusuowei.shiro_jwt.utils.RedisUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisUtil redisUtil;

    @RequiresAuthentication
    @GetMapping("/members")
    public R members() {
        List<User> list = userService.list();
        int q1 = 0; // 第一季度
        int q2 = 0; // 第二季度
        int q3 = 0; // 第三季度
        int q4 = 0; // 第四季度
        for (User user : list) {
            LocalDateTime localDateTime = user.getCreateTime();
            Date createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            System.out.println(createTime);

            //Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter) {
                case Q1:
                    q1 += 1;
                    break;
                case Q2:
                    q2 += 1;
                    break;
                case Q3:
                    q3 += 1;
                    break;
                case Q4:
                    q4 += 1;
                    break;
                default:
                    break;
            }
        }
        return R.ok().setData(CollUtil.newArrayList(q1, q2, q3, q4));
    }

    @RequiresAuthentication
    @GetMapping("/allTotal")
    public R allTotal() {
        long utotal = userService.count();
        long rtotal = roleService.count();
        long mtotal = menuService.count();
        long ototal = redisUtil.hGetMapSize("refreshToken");
        HashMap<String, Long> map = new HashMap<>();
        map.put("utotal",utotal);
        map.put("rtotal",rtotal);
        map.put("mtotal",mtotal);
        map.put("ototal",ototal);
        return R.ok().setData(map);
    }
}

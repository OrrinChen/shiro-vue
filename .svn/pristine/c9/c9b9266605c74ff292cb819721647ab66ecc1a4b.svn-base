package com.wusuowei.shiro_jwt.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wusuowei.shiro_jwt.model.po.User;
import com.wusuowei.shiro_jwt.model.po.UserRole;
import com.wusuowei.shiro_jwt.model.vo.UserPasswordVo;
import com.wusuowei.shiro_jwt.service.RoleService;
import com.wusuowei.shiro_jwt.service.UserRoleService;
import com.wusuowei.shiro_jwt.service.UserService;
import com.wusuowei.shiro_jwt.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author LGY
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    /**
     * @description 新增和修改
     * @param user 使用者
     * @return {@link R }
     * @author LGY
     * @date 2023/03/24 21:10
     */
    @RequiresRoles(value = {"admin","vip"},logical = Logical.OR)
    @Transactional
    @PostMapping
    public R save(@RequestBody User user) {
        // 新增或者更新
        userService.saveOrUpdate(user);
        UserRole userRole = new UserRole();

        userRole.setUserId(String.valueOf(user.getId()));

        if(user.getRoleid()!=null){
            //把原来的关系删了
            userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,userRole.getUserId()));
            //在进行添加
            for (Integer roleid : user.getRoleid()) {
                userRole.setRoleId(String.valueOf(roleid));
                userRoleService.save(userRole);
            }
        }
        return R.ok();
    }

    /**
     * @description 查找页面
     * @param pageNum 书籍页码
     * @param pageSize 页面大小
     * @param username 用户名
     * @param email 电子邮件
     * @param address 住址
     * @return {@link Map }<{@link String }, {@link Object }>
     * @author LGY
     * @date 2023/03/24 21:11
     */
    @RequiresRoles(value = {"admin","vip"},logical = Logical.OR)//满足一个角色就能访问
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam(required = false) String username,
                                        @RequestParam(required = false) String email,
                                        @RequestParam(required = false) String address) {

        Page<User> page = userService.findPage(new Page<>(pageNum, pageSize), username, email, address);
        List<User> records = page.getRecords();
        List<User> collect = records.stream().map(item -> {
            item.setRole(roleService.getRoles(String.valueOf(item.getId())));
            return item;
        }).collect(Collectors.toList());
        page.setRecords(collect);
        return R.ok().setData(page);
    }

    @RequiresRoles("admin")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        userService.removeById(id);
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,id));
        return R.ok();
    }

    /**
     * @description 批量删除
     * @param ids
     * @return {@link R }
     * @author LGY
     * @date 2023/03/24 21:10
     */
    @RequiresRoles("admin")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        userService.removeBatchByIds(ids);
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().in(UserRole::getUserId,ids));
        return R.ok();
    }

    /**
     * @description 根据用户名返回用户
     * @param username 用户名
     * @return {@link R }
     * @author LGY
     * @date 2023/03/24 21:09
     */
    @RequiresAuthentication
    @GetMapping("/username/{username}")
    public R getByUsername(@PathVariable String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User one = userService.getOne(wrapper);
        return R.ok().setData(one);
    }

    @RequiresAuthentication
    @PostMapping("/password")
    public R password(@RequestBody UserPasswordVo userPasswordVo) {
        userPasswordVo.setPassword(DigestUtils.md5DigestAsHex(userPasswordVo.getPassword().getBytes()));
        userPasswordVo.setNewPassword(DigestUtils.md5DigestAsHex(userPasswordVo.getNewPassword().getBytes()));
        try {
            userService.updatePassword(userPasswordVo);
        } catch (Exception e) {
            return R.error("原密码错误");
        }
        return R.ok();
    }

}

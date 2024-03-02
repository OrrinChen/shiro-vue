package com.wusuowei.shiro_jwt.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wusuowei.shiro_jwt.model.po.Role;
import com.wusuowei.shiro_jwt.model.po.UserRole;
import com.wusuowei.shiro_jwt.service.RoleService;
import com.wusuowei.shiro_jwt.service.UserRoleService;
import com.wusuowei.shiro_jwt.utils.R;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lgy
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    // 新增或者更新
    @RequiresRoles(value = {"admin","vip"},logical = Logical.OR)
    @PostMapping
    public R save(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return R.ok();
    }

    @RequiresAuthentication
    @GetMapping
    public R findAll() {
        return R.ok().setData(roleService.list());
    }

    @RequiresRoles("admin")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        long count = userRoleService.count(new LambdaQueryWrapper<UserRole>().eq(UserRole::getRoleId, id));
        if(count>0){
            return R.error("无法删除，该角色还有绑定的用户");
        }
        roleService.removeById(id);
        return R.ok();
    }

    @RequiresRoles("admin")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        long count = userRoleService.count(new LambdaQueryWrapper<UserRole>().in(UserRole::getRoleId, ids));
        if(count>0){
            return R.error("无法删除，该角色还有绑定的用户");
        }
        roleService.removeByIds(ids);
        return R.ok();
    }

    @RequiresAuthentication
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.ok().setData(roleService.getById(id));
    }

    @RequiresAuthentication
    @GetMapping("/page")
    public R findPage(@RequestParam String name,
                      @RequestParam Integer pageNum,
                      @RequestParam Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByAsc("id");
        return R.ok().setData(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     * 绑定角色和菜单的关系
     * @param roleId 角色id
     * @param menuIds 菜单id数组
     * @return
     */
    @RequiresRoles(value = {"admin","vip"},logical = Logical.OR)
    @PostMapping("/roleMenu/{roleId}")
    public R roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId, menuIds);
        return R.ok();
    }

    @RequiresAuthentication
    @GetMapping("/roleMenu/{roleId}")
    public R getRoleMenu(@PathVariable Integer roleId) {
        return R.ok().setData(roleService.getRoleMenu(roleId));
    }

}


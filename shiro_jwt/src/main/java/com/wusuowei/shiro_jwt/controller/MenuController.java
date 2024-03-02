package com.wusuowei.shiro_jwt.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wusuowei.shiro_jwt.mapper.DictMapper;
import com.wusuowei.shiro_jwt.model.po.Dict;
import com.wusuowei.shiro_jwt.model.po.Menu;
import com.wusuowei.shiro_jwt.model.po.RoleMenu;
import com.wusuowei.shiro_jwt.service.MenuService;
import com.wusuowei.shiro_jwt.service.RoleMenuService;
import com.wusuowei.shiro_jwt.utils.R;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LGY
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private DictMapper dictMapper;

    // 新增或者更新
    @RequiresRoles(value = {"admin","vip"},logical = Logical.OR)
    @PostMapping
    public R save(@RequestBody Menu menu) {
        menuService.saveOrUpdate(menu);
        return R.ok();
    }

    @RequiresRoles("admin")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        menuService.removeById(id);
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getMenuId,id));
        return R.ok();
    }

    @RequiresRoles("admin")
    @PostMapping("/del/batch")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        menuService.removeByIds(ids);
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().in(RoleMenu::getMenuId,ids));
        return R.ok();
    }

    @RequiresAuthentication
    @GetMapping("/ids")
    public R findAllIds() {
        return R.ok().setData(menuService.list().stream().map(Menu::getId).collect(Collectors.toList()));
    }

    @RequiresAuthentication
    @GetMapping
    public R findAll(@RequestParam(defaultValue = "") String name) {
        return R.ok().setData(menuService.findMenus(name));
    }

    @RequiresAuthentication
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.ok().setData(menuService.getById(id));
    }

    @RequiresAuthentication
    @GetMapping("/icons")
    public R getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", "icon");
        return R.ok().setData(dictMapper.selectList(queryWrapper));
    }

}


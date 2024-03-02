package com.wusuowei.shiro_jwt.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wusuowei.shiro_jwt.mapper.RoleMapper;
import com.wusuowei.shiro_jwt.mapper.RoleMenuMapper;
import com.wusuowei.shiro_jwt.model.po.Menu;
import com.wusuowei.shiro_jwt.model.po.Role;
import com.wusuowei.shiro_jwt.model.po.RoleMenu;
import com.wusuowei.shiro_jwt.service.MenuService;
import com.wusuowei.shiro_jwt.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author LGY
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private MenuService menuService;


    @Override
    public List<Role> getRoles(String uid) {

        QueryWrapper<Role> wrapper = new QueryWrapper<Role>().inSql("id", "SELECT role_id FROM sys_user_role WHERE user_id=" + uid);
        List<Role> list = this.list(wrapper);
        return list;
    }

    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {

        // 先删除当前角色id所有的绑定关系
        roleMenuMapper.deleteByRoleId(roleId);

        // 再把前端传过来的菜单id数组绑定到当前的这个角色id上去
        List<Integer> menuIdsCopy = CollUtil.newArrayList(menuIds);
        for (Integer menuId : menuIds) {
            Menu menu = menuService.getById(menuId);
            if (menu.getPid() != null && !menuIdsCopy.contains(menu.getPid())) { // 二级菜单 并且传过来的menuId数组里面没有它的父级id
                // 那么我们就得补上这个父级id
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);
                menuIdsCopy.add(menu.getPid());
            }
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {

        return roleMenuMapper.selectByRoleId(roleId);
    }


}

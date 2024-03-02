package com.wusuowei.shiro_jwt.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wusuowei.shiro_jwt.mapper.MenuMapper;
import com.wusuowei.shiro_jwt.model.po.Menu;
import com.wusuowei.shiro_jwt.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author LGY
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * @description 模糊查找菜单
     * @param name 名称
     * @return {@link List }<{@link Menu }>
     * @author LGY
     * @date 2023/04/01 10:39
     */
    @Override
    public List<Menu> findMenus(String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort_num");
        if (StrUtil.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        // 查询所有数据
        List<Menu> list = list(queryWrapper);
        // 找出pid为null的一级菜单
        List<Menu> parentNodes = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        // 找出一级菜单的子菜单
        for (Menu menu : parentNodes) {
            // 筛选所有数据中pid=父级id的数据就是二级菜单
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return parentNodes;
    }

    @Override
    public List<Menu> getPermissionByUid(String uid) {
        return menuMapper.getPermissionByUid(uid);
    }

    /**
     * @description 获取用户菜单
     * @param id
     * @return {@link List }<{@link Menu }>
     * @author LGY
     * @date 2023/04/01 10:43
     */
    @Override
    public List<Menu> getUserMenus(Integer id) {
        List<Menu> userMenus = menuMapper.getUserMenus(id);
        List<Menu> collect = userMenus.stream().filter(item -> {
            return item.getPid() == null;
        }).map(menu -> {
            menu.setChildren(getChildren(userMenus, menu));
            return menu;
        }).collect(Collectors.toList());
        return collect;
    }

    private List<Menu> getChildren(List<Menu> userMenus, Menu menu) {

        List<Menu> collect = userMenus.stream().filter(item -> {
            return item.getPid()!=null && item.getPid().equals(menu.getId());
        }).map(children -> {
            children.setChildren(getChildren(userMenus, children));
            return children;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Menu> getPermission(Set<Integer> rids) {

        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<Menu>().inSql(Menu::getId, "SELECT menu_id FROM sys_role_menu WHERE role_id in (1,2)");
        List<Menu> list = this.list(wrapper);
        return list;
    }
}

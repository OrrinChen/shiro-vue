package com.wusuowei.shiro_jwt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wusuowei.shiro_jwt.model.po.Menu;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author LGY
 * @since 2023-04-17
 */
public interface MenuService extends IService<Menu> {

    List<Menu> getPermission(Set<Integer> rids);

    List<Menu> getUserMenus(Integer id);

    Object findMenus(String name);

    List<Menu> getPermissionByUid(String uid);
}

package com.wusuowei.shiro_jwt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wusuowei.shiro_jwt.model.po.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author LGY
 * @since 2023-04-17
 */
public interface RoleService extends IService<Role> {

    List<Role> getRoles(String uid);

    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);
}

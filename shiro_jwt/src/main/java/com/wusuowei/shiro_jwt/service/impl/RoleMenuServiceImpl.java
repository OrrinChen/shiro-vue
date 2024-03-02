package com.wusuowei.shiro_jwt.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wusuowei.shiro_jwt.mapper.RoleMenuMapper;
import com.wusuowei.shiro_jwt.model.po.RoleMenu;
import com.wusuowei.shiro_jwt.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-菜单-关联表 服务实现类
 * </p>
 *
 * @author LGY
 */
@Slf4j
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}

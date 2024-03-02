package com.wusuowei.shiro_jwt.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wusuowei.shiro_jwt.mapper.UserRoleMapper;
import com.wusuowei.shiro_jwt.model.po.UserRole;
import com.wusuowei.shiro_jwt.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色关联表 服务实现类
 * </p>
 *
 * @author LGY
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

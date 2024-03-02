package com.wusuowei.shiro_jwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wusuowei.shiro_jwt.mapper.UserMapper;
import com.wusuowei.shiro_jwt.mapper.UserRoleMapper;
import com.wusuowei.shiro_jwt.model.po.User;
import com.wusuowei.shiro_jwt.model.po.UserRole;
import com.wusuowei.shiro_jwt.model.vo.UserPasswordVo;
import com.wusuowei.shiro_jwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LGY
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public User getUserByPass(String username, String md5Password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<UserRole> wrapper2 = new LambdaQueryWrapper<>();
        User user = userMapper.selectOne(wrapper.eq(User::getUsername, username).eq(User::getPassword, md5Password));
        if(user!=null){
            List<String> rids = userRoleMapper.selectList(wrapper2.eq(UserRole::getUserId, user.getId())).stream()
                    .map(UserRole::getRoleId).collect(Collectors.toList());
            user.setRole(roleService.getRoles(String.valueOf(user.getId())));
        }
        return user;
    }

    @Override
    public Page<User> findPage(Page<User> page, String username, String email, String address) {
        return userMapper.findPage(page, username, email, address);
    }

    @Override
    public void updatePassword(UserPasswordVo userPasswordVo) {
        int update = userMapper.updatePassword(userPasswordVo);
        if (update < 1) {
            throw new RuntimeException("密码错误");
        }
    }

}

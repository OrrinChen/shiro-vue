package com.wusuowei.shiro_jwt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wusuowei.shiro_jwt.model.po.User;
import com.wusuowei.shiro_jwt.model.vo.UserPasswordVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LGY
 * @since 2023-04-19
 */
public interface UserService extends IService<User> {

    User getUserByPass(String username, String md5Password);

    Page<User> findPage(Page<User> objectPage, String username, String email, String address);

    void updatePassword(UserPasswordVo userPasswordVo);
}

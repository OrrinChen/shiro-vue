package com.wusuowei.shiro_jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wusuowei.shiro_jwt.model.po.User;
import com.wusuowei.shiro_jwt.model.vo.UserPasswordVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LGY
 */
public interface UserMapper extends BaseMapper<User> {

    Page<User> findPage(Page<User> page, @Param("username") String username,@Param("email") String email,@Param("address") String address);


    int updatePassword(UserPasswordVo userPasswordVo);
}

package com.wusuowei.shiro_jwt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wusuowei.shiro_jwt.model.po.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色-菜单-关联表 Mapper 接口
 * </p>
 *
 * @author LGY
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    int deleteByRoleId(@Param("roleId") Integer roleId);

    @Select("SELECT * FROM sys_menu WHERE id IN (select menu_id from sys_role_menu where role_id = #{roleId})")
    List<Integer> selectByRoleId(@Param("roleId")Integer roleId);
}

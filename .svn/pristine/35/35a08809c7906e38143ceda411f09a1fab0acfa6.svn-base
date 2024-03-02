package com.wusuowei.shiro_jwt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wusuowei.shiro_jwt.model.po.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author LGY
 */
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("SELECT * FROM sys_menu WHERE id in (SELECT menu_id FROM sys_role_menu WHERE role_id in(SELECT role_id FROM sys_user_role where user_id = #{id})) ORDER BY sort_num ASC")
    List<Menu> getUserMenus(Integer id);

    @Select("SELECT * FROM sys_menu WHERE id in(SELECT menu_id from sys_role_menu WHERE role_id IN (SELECT role_id FROM sys_user_role WHERE user_id = #{uid}))")
    List<Menu> getPermissionByUid(String id);
}

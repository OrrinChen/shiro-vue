package com.wusuowei.shiro_jwt.shiro;


import com.wusuowei.shiro_jwt.model.po.Menu;
import com.wusuowei.shiro_jwt.model.po.Role;
import com.wusuowei.shiro_jwt.model.po.User;
import com.wusuowei.shiro_jwt.service.MenuService;
import com.wusuowei.shiro_jwt.service.RoleService;
import com.wusuowei.shiro_jwt.service.UserService;
import com.wusuowei.shiro_jwt.utils.JWTUtil;
import com.wusuowei.shiro_jwt.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MyRealm extends AuthorizingRealm {

    @Value("${jwt.refreshtoken-expire}")
    //refreshtoken-expire续期过期时间
    private Long REFRESHTOKENEXPIRE;
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;


    @Autowired
    private RedisUtil redisUtil;

    //根据token判断此Authenticator是否使用该realm
    //必须重写不然shiro会报错
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如@RequiresRoles,@RequiresPermissions之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权~~~~~");
        User user = (User) principals.getPrimaryPrincipal();
        String uid = String.valueOf(user.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> redisRoles = (List<Role>) redisUtil.hget("userPower", "roles:"+uid);
        List<Menu> redisPermissions = (List<Menu>) redisUtil.hget("userPower", "permission:"+uid);
        if (redisRoles != null && redisPermissions != null) {
            info.addRoles(redisRoles.stream().map(Role::getRoleKey).collect(Collectors.toSet()));
            info.addStringPermissions(redisPermissions.stream().filter(item -> StringUtils.isNotBlank(item.getPermission())).map(Menu::getPermission).collect(Collectors.toSet()));
            return info;
        }
        //查询数据库来获取用户的角色
        List<Role> roles = roleService.getRoles(uid);
        info.addRoles(roles.stream().map(Role::getRoleKey).collect(Collectors.toSet()));

        //查询数据库来获取用户的权限
        List<Menu> permissions = menuService.getPermissionByUid(uid);
        Set<String> collect = permissions.stream().filter(item -> StringUtils.isNotBlank(item.getPermission())).map(Menu::getPermission).collect(Collectors.toSet());
        info.addStringPermissions(collect);
        redisUtil.hset("userPower", "roles:" + uid, roles, REFRESHTOKENEXPIRE);
        redisUtil.hset("userPower", "permissions:" + uid, collect, REFRESHTOKENEXPIRE);
        return info;
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可，在需要用户认证和鉴权的时候才会调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证~~~~~~~");
        String jwt = (String) token.getCredentials();
        String uid = JWTUtil.getUserId(jwt);
        User redisUser = (User) redisUtil.get("userInfo:" + uid);
        if (redisUser != null) {
            return new SimpleAuthenticationInfo(redisUser, jwt, "MyRealm");
        }
        User user = userService.getById(uid);
        if (user == null) {
            throw new AuthenticationException("该用户不存在");
        }
        redisUtil.hset("userInfo",uid, user, REFRESHTOKENEXPIRE);
        return new SimpleAuthenticationInfo(user, jwt, "MyRealm");
    }
}

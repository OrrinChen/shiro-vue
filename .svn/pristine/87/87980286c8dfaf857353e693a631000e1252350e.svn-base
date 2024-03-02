package com.wusuowei.shiro_jwt;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wusuowei.shiro_jwt.mapper.UserMapper;
import com.wusuowei.shiro_jwt.model.po.Menu;
import com.wusuowei.shiro_jwt.model.po.Role;
import com.wusuowei.shiro_jwt.model.po.User;
import com.wusuowei.shiro_jwt.service.MenuService;
import com.wusuowei.shiro_jwt.service.RoleService;
import com.wusuowei.shiro_jwt.service.UserService;
import com.wusuowei.shiro_jwt.utils.JWTUtil;
import com.wusuowei.shiro_jwt.utils.RedisUtil;
import com.wusuowei.shiro_jwt.utils.SpringContextUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
class ShiroJwtApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

   // private RedisUtil redisUtil = (RedisUtil) SpringContextUtils.getBean(RedisUtil.class);

    //private JWTUtil jwtUtil = (JWTUtil) SpringContextUtils.getBean(JWTUtil.class);
    @Test
    void contextLoads() {
        //redisTemplate.opsForValue().set("李光耀","lala啦啦啦");
       // System.out.println(new Date());

        RedisUtil bean = SpringContextUtils.getBean(RedisUtil.class);
        System.out.println(bean);
        System.out.println(bean.get("1"));
        //System.out.println(redisUtil);
        //System.out.println(jwtUtil);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;


    @Autowired
    private RedisUtil redisUtil;



    @Test
    void testRedis(){
        redisUtil.set("1",userService.getById("1"));
    }

    @Test
    void getRedisUser(){
        String userId = JWTUtil.getUserId("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxIiwiZXhwIjoxNjgyMTUwOTc0LCJpYXQiOjE2ODIxNTA5NzN9.7LA9OjkByAXSIze_agl-5iuS3LGDLRLSH5c3zghVmh8");
        User user = (User) redisUtil.get(userId);
        System.out.println(user);
    }

    @Test
    void testJWT(){
        boolean verify = JWTUtil.verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxIiwiZXhwIjoxNjgyMTUwOTc0LCJpYXQiOjE2ODIxNTA5NzN9.7LA9OjkByAXSIze_agl-5iuS3LGDLRLSH5c3zghVmh8");
        System.out.println(verify);
    }
    @Test
    void setRoles(){
        List<Role> roles = roleService.getRoles("1");
        //查询数据库来获取用户的权限
        Set<Integer> rids = roles.stream().map(Role::getId).collect(Collectors.toSet());
        List<Menu> menus = menuService.getPermission(rids);
        redisUtil.hset("userpower","roles:1",roles);
        redisUtil.hset("userpower","permission:1",menus);
    }

    @Test
    void getUserList(){
        Page<User> page = userService.findPage(new Page<User>(1, 5), "", "", "");
        List<User> records = page.getRecords();
        System.out.println(records);
    }

    @Autowired
    UserMapper userMapper;

    public static void main(String[] args) {

        String s1 = new String("张三");
        change(s1);
        System.out.println(s1);
    }

    @Test
    public void testRedisSet(){
        Long refreshtest = redisTemplate.opsForHash().size("refreshtest");
        System.err.println(refreshtest);
    }

    @Test
    public void testUserRole(){
        Page<User> page = userMapper.findPage(new Page<>(1, 20), null, null, null);
        System.out.println(page.getRecords().get(0));
    }
    private static void change(String s2) {
        s2 = "李四";
    }
}

package com.tan.shiro;

import com.tan.pojo.LoginUser;
import com.tan.service.StuUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 * 自定义Realm
 *
 *
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//        System.out.println("执行授权逻辑");
////        //给资源进行授权

        return null;
    }

    @Qualifier("stuUserServiceImpl")
    @Autowired
    private StuUserService userSerivce;
    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");


        //假设这是数据库获取的用户名和密码
      //        String name = "aa";
      //        String password = "123456";

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)arg0;

        LoginUser user = userSerivce.findByUsername(token.getUsername());

        if(user==null){
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }

        //2.判断密码
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }

}
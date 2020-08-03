package com.tan.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置类
 *
 *
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("MySecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();

        filterMap.put("/static/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/webjars/**", "anon");

        filterMap.put("/stu/toLogin", "anon");
        filterMap.put("/stu/loginsubmit", "anon");
        filterMap.put("/sturegister", "anon");
        filterMap.put("/StuRegister", "anon");
        filterMap.put("/findReisterUsername","anon");
        filterMap.put("/alogin", "anon");
        filterMap.put("/adminLoginSubmit", "anon");

        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/stu/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/stu/toLogin");
//        shiroFilterFactoryBean.setLoginUrl("/alogin");
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterMap.put("/logout", "logout");
        //放最后
        filterMap.put("/*", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="MySecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}

package top.shiyana.blog.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.shiyana.blog.realm.UserRealm;

/**
 * @ProjectName: blog
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/13 16:12
 * @Version: 1.0
 */
//@Configuration标记该类是一个配置类
@Configuration
public class ShiroConfig {

    /**
     * 1.注入UserRealm
     * @return
     */
    @Bean
    public UserRealm getUserRealm() {
        return new UserRealm();
    }

    /**
     * 	创建ShiroFilterFactoryBean过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        //创建ShiroFilterFactoryBean对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全会话管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
         *	Shiro内置过滤器，可以实现权限相关的拦截器
         *	常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       roles: 该资源必须得到角色权限才可以访问
         */
        //创建Map集合，保存各种需要处理的请求
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        /**********************设置放行anon请求***************************/
        filterChainDefinitionMap.put("/source/**","anon");//放行静态资源
        filterChainDefinitionMap.put("/login.html", "anon");//去到登录页面操作
        filterChainDefinitionMap.put("/login", "anon");//登录操作
        filterChainDefinitionMap.put("/admin/user/**", "anon");//注册以及发送验证码操作
        filterChainDefinitionMap.put("/","anon");//首页
        filterChainDefinitionMap.put("/index*","anon");//首页
        filterChainDefinitionMap.put("/blogType/**","anon");//前台博客类型
        filterChainDefinitionMap.put("/blog/**","anon");//前台博客
        filterChainDefinitionMap.put("/comment/**","anon");//评论
        filterChainDefinitionMap.put("/blogger/**","anon");//评论
        /**********************设置身份验证authc请求***************************/


        //此配置放到最后
        filterChainDefinitionMap.put("/**", "authc");//所有请求必须进行登录

        //设置过滤器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //设置登录页面请求（不设置默认去到login.jsp页面）
        shiroFilterFactoryBean.setLoginUrl("/login");

        //返回shiroFilterFactoryBean
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager对象，关联Realm
     * @param realm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm realm) {
        //创建DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm
        defaultWebSecurityManager.setRealm(realm);
        //返回DefaultWebSecurityManager
        return defaultWebSecurityManager;
    }
}

package top.shiyana.blog.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import top.shiyana.blog.pojo.entity.TBlogger;
import top.shiyana.blog.service.ITBloggerService;

import javax.annotation.Resource;

/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.realm
 * @ClassName: UserRealm
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/13 16:12
 * @Version: 1.0
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private ITBloggerService bloggerService;

    /**
     * 验证权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 验证身份（登录）
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            //得到当前用户名
            String email = (String) token.getPrincipal();
            //调用根据用户名查询博主信息的方法
            TBlogger blogger = bloggerService.findBloggerByEmail(email);
            //对象不为空，表示存在此人，用户名验证通过
            if(blogger!=null){
                //创建验证身份对象
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(blogger.getEmail(),blogger.getPassword(),"");
                return authenticationInfo;//登录成功
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //登录失败
        return null;
    }
}

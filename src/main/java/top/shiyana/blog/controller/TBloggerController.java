package top.shiyana.blog.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import top.shiyana.blog.pojo.entity.TBlogger;
import top.shiyana.blog.service.ITBloggerService;
import top.shiyana.blog.util.SysConstant;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
@Controller
@RequestMapping("/blogger")
public class TBloggerController {

    @Autowired
    private ITBloggerService service;

    @PostMapping("login")
    public String login(TBlogger blogger){
        try {
            //获取认证实体
            Subject subject = SecurityUtils.getSubject();
            //创建认证令牌Token
            UsernamePasswordToken token = new UsernamePasswordToken(blogger.getEmail(),blogger.getPassword());
            //执行登录（会报异常 如果进行到下一步表示登录成功）
            subject.login(token);
            //将用户信息保存到session中
            subject.getSession().setAttribute(SysConstant.LOGINUSER,blogger);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //认证失败 返回登录页面
            return "redirect:/login";
        }
        //认证成功 进入首页
        return "redirect:/admin/index";
    }


   /* @PostMapping("/login")
    public String login(TBlogger blogger){
        //1.得到当前登录用户（主体）
        Subject subject= SecurityUtils.getSubject();
        try {
            //2.创建登录令牌
            UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUserName(),blogger.getPassword());
            //3.登录
            subject.login(token);
            //登录成功 保存当前登录用户
            subject.getSession().setAttribute(SysConstant.LOGINUSER,blogger);
        } catch (Exception e) {
            e.printStackTrace();
            //认证失败 返回登录页面
            return "redirect:/login.html";
        }
        //登录成功去到后台首页
        return "redirect:/admin/index";
    }*/
}
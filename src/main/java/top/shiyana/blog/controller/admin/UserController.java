package top.shiyana.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.shiyana.blog.pojo.dto.BloggerDTO;
import top.shiyana.blog.service.ITBloggerService;
import top.shiyana.blog.util.EmailUtils;
import top.shiyana.blog.util.RedisUtil;
import top.shiyana.hospital.vo.ResponseResult;


/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.controller.admin
 * @ClassName: UserController
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/25 10:47
 * @Version: 1.0
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ITBloggerService service;

    @RequestMapping("/email/{email}")
    @ResponseBody
    public ResponseResult email(@PathVariable("email") String email){
        if(email != null && email.length()>5){
            String code = emailUtils.sendHtmlEmail(email);
            boolean set = redisUtil.set(email+":login_code", code, 120);
            if(set){
                return new ResponseResult(200, "登录成功！！！");
            }
        }
        return null;
    }

    @RequestMapping("register")
    @ResponseBody
    public ResponseResult register(@RequestBody BloggerDTO blogger){

        if(blogger.getCode()!= null && blogger.getCode().length() == 6 ){
            Boolean b = service.register(blogger);
            if(b){
                return new ResponseResult(200, "注册成功！！！");
            }
        }
        return new ResponseResult(500, "验证失败！！！");
    }
}

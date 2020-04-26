package top.shiyana.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.controller.admin
 * @ClassName: AdminLoginController
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/13 15:41
 * @Version: 1.0
 */
@Controller
@RequestMapping("admin")
public class AdminLoginController {

    @RequestMapping("index")
    public String home(){
        return "admin/home";
    }
}

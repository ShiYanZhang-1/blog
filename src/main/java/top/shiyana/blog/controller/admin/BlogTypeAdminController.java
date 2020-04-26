package top.shiyana.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.shiyana.blog.service.ITBlogtypeService;

/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.controller.admin
 * @ClassName: BlogTypeAdminController
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/13 17:27
 * @Version: 1.0
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

    @Autowired
    private ITBlogtypeService service;

    @RequestMapping("typeItem")
    @ResponseBody
    private String typeItem(){
        try {
            String count = service.getBlogTypeIdAndBlogCount();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

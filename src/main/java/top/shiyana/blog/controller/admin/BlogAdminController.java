package top.shiyana.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.shiyana.blog.pojo.entity.TBlog;
import top.shiyana.blog.service.ITBlogService;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.controller.admin
 * @ClassName: BlogAdminController
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/13 16:57
 * @Version: 1.0
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Autowired
    private ITBlogService service;

    @RequestMapping("writeBlog")
    public String writeBlog(){
        return "/admin/writeBlog";
    }

    /**
     * 新增博客
     * @param blog
     * @return
     */
    @ResponseBody
    @RequestMapping("addBlog")
    public String addBlog(TBlog blog){
        Map<String,Object> map = new HashMap<>();
        try {
            //将博客添加到数据库中
            int count = service.addBlog(blog);

            //blogIndex.addIndex(blog);

            if(count>0){
                map.put("success",true);
            }else {
                map.put("success",false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}

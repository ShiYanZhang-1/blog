package top.shiyana.blog.controller.foreign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.shiyana.blog.pojo.entity.TBlog;
import top.shiyana.blog.service.ITBlogService;
import top.shiyana.blog.util.PageUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.controller.foreign
 * @ClassName: indexController
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/12 21:01
 * @Version: 1.0
 */
@Controller
public class indexController {

    @Resource
    private ITBlogService service;

    /**
     * 根据条件查询数据列表
     * @param blog 与博客相关的查询条件
     * @param current 当前页数
     * @param model 数据列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/","/index","/index.html"})
    public String index(TBlog blog, @RequestParam(value = "page" ,defaultValue = "1") Long current, Model model) throws Exception {
        int size = 5;
        //创建分页对象，设置默认的分页参数
        Page<TBlog> page = new Page<TBlog>(current,5);
        //查询出分页对象
        IPage<TBlog> blogByPage = service.findBlogByPage(page, blog);
        //获取查询到的博客列表
        List<TBlog> blogList = blogByPage.getRecords();

        StringBuffer param = new StringBuffer();
        if(blog != null){
            if(blog.getTypeId()!=null){
                param.append("typeId="+blog.getTypeId());
            }
            if(StringUtils.isNoneBlank(blog.getReleaseDateStr())){
                param.append("releaseDateStr="+blog.getReleaseDateStr());
            }
        }
        model.addAttribute("pageContent","foreign/main");
        model.addAttribute("pageCode",  PageUtil.genPagination("/index.html",blogByPage.getTotal(),current.intValue(),size,param.toString()));
        //放到Model传到前端展示
        model.addAttribute("blogList",blogList);
        //返回首页
        return "index";
    }
}

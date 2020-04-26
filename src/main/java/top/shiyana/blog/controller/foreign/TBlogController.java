package top.shiyana.blog.controller.foreign;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import top.shiyana.blog.pojo.entity.TBlog;
import top.shiyana.blog.service.ITBlogService;
import top.shiyana.blog.service.ITCommentService;
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
@RequestMapping("/blog")
public class TBlogController {

    @Autowired
    private ITBlogService service;

    @Autowired
    private ITCommentService commentService;

    @ResponseBody
    @RequestMapping("blogDateList")
    public String blogDateList(){
        try {
            //查询博客时间以及数量
            return service.findBlogDataAndCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 显示博客详情信息
     * @param id 博客id
     * @param model 数据model
     * @return
     */
    @GetMapping("view/{id}")
    public String view(@PathVariable(value = "id") int id, Model model){
        try {
            TBlog blog = service.findBlogById(id);
            if(blog!=null && StringUtils.isNoneBlank(blog.getKeyWord())){
                //关键字不为空，将数据库已空格分隔的关键字拆分为数组
                String[] keyWordList = blog.getKeyWord().split(" ");
                model.addAttribute("keyWordList",keyWordList);
            }else{
                //没有关键字
                model.addAttribute("keyWordList",null);
            }
            model.addAttribute("pageCode",getUpAndDownPageContent(service.findPreBlogById(id),service.findNextBlogById(id)));
            model.addAttribute("blog",blog);
            model.addAttribute("commentList",commentService.findCommentByBlogId(id, SysConstant.COMMENT_STATE_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("pageContent","foreign/blog/view");
        return "index";
    }


    /**
     * 获取当前博客的上一篇和下一篇
     * @param pre
     * @param next
     * @return
     */
    public String getUpAndDownPageContent(TBlog pre,TBlog next){
        StringBuffer sb = new StringBuffer();
        //判断有没有上一篇博客
        if(pre!=null && pre.getId()!= null){
            //有上一篇博客
            sb.append("<p>上一篇：<a href='/blog/view/"+pre.getId()+"'>"+pre.getTitle()+"</a></p>");
        }else {
            sb.append("<p>没有上一篇了</p>");
        }

        //判断有没有下一篇博客
        if(next!=null && next.getId()!= null){
            //有下一篇博客
            sb.append("<p>下一篇：<a href='/blog/view/"+next.getId()+"'>"+next.getTitle()+"</a></p>");
        }else {
            sb.append("<p>没有下一篇了</p>");
        }
        return sb.toString();
    }



}

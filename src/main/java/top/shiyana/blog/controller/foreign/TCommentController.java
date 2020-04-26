package top.shiyana.blog.controller.foreign;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.shiyana.blog.pojo.entity.TComment;
import top.shiyana.blog.service.ITCommentService;
import top.shiyana.blog.util.GetIp;
import top.shiyana.blog.util.SysConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *  /comment/addComment
 * @author 誓言
 * @since 2020-04-12
 */
@RestController
@RequestMapping("comment")
public class TCommentController {

    @Autowired
    private ITCommentService service;

    @PostMapping("addComment")
    public String addComment(TComment comment, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        comment.setCommentDate(new Date());//当前系统时间
        comment.setState(SysConstant.COMMENT_STATE_FALSE);//默认待审核
        comment.setUserIp(GetIp.getIpAddress(request));
        boolean save = service.save(comment);
        if(save){
            map.put("success",true);
        }else {
            map.put("success",false);
        }
        return JSON.toJSONString(map);
    }

}

package top.shiyana.blog.controller.foreign;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.shiyana.blog.service.ITBlogtypeService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/blogType")
public class TBlogtypeController {

    @Autowired
    private ITBlogtypeService service;

    @ResponseBody
    @GetMapping("typeList")
    public String typeList(){
        try {
            return service.getBlogTypeIdAndBlogCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

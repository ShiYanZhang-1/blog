package top.shiyana.blog.controller.admin;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.controller.admin
 * @ClassName: UEditorController
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/13 17:59
 * @Version: 1.0
 */
@Controller
public class UEditorController {

    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        //设置内容类型
        response.setContentType("application/json");
        //图片上传的服务器地址，当前项目的所在路径不得出现中文
        String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/source/ueditor/jsp";
        try {
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            //以下代码是百度编辑器的配置
            String exec = new ActionEnter(request, rootPath).exec();
            System.out.println(exec);
            PrintWriter writer = response.getWriter();
            writer.write(new ActionEnter( request, rootPath ).exec());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

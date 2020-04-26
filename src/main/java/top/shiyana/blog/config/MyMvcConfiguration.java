package top.shiyana.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.config
 * @ClassName: MyMvcCobfigurtion
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/12 14:54
 * @Version: 1.0
 */
@Configuration
public class MyMvcConfiguration implements WebMvcConfigurer {

    //设置登录默认界面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");//根目录
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //设置登录页面
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
    }

}

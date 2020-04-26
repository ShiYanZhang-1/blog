package top.shiyana.blog.config;

/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.config
 * @ClassName: PageMPConfig
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/12 20:47
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement//开启事务管理器
@MapperScan("top.shiyana.blog.mapper")//加载mapper接口
public class PageMPConfig {
    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

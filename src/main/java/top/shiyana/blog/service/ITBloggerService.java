package top.shiyana.blog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.shiyana.blog.pojo.dto.BloggerDTO;
import top.shiyana.blog.pojo.entity.TBlogger;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
public interface ITBloggerService extends IService<TBlogger> {

     /**
     * 根据博主名称查询博主信息
     * @param email
     * @return
     */
    TBlogger findBloggerByEmail(String email) throws Exception;

    /**
     * 根据邮箱注册
     * @param blogger
     * @return
     */
    Boolean register(BloggerDTO blogger);
}

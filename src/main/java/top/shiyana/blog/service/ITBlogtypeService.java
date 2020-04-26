package top.shiyana.blog.service;

import top.shiyana.blog.pojo.entity.TBlogtype;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
public interface ITBlogtypeService extends IService<TBlogtype> {

    String getBlogTypeIdAndBlogCount() throws Exception;
}

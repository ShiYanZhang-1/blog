package top.shiyana.blog.mapper;

import top.shiyana.blog.pojo.entity.TBlogtype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
public interface TBlogtypeMapper extends BaseMapper<TBlogtype> {

    List<TBlogtype> getBlogTypeIdAndBlogCount() throws Exception;

}

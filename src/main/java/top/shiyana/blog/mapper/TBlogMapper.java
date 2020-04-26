package top.shiyana.blog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import top.shiyana.blog.pojo.entity.TBlog;
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
public interface TBlogMapper extends BaseMapper<TBlog> {

    //查询博客日期和数量
    List<TBlog> findBlogDataAndCount() throws Exception;

    /**
     * 分页查询博客信息
     * @param page
     * @param blog
     * @return
     * @throws Exception
     */
    IPage<TBlog> findBlogByPage(@Param("page") IPage<TBlog> page, @Param("blog") TBlog blog) throws Exception;

    /**
     * 通过Id查询博客详情
     * @param id
     * @return
     * @throws Exception
     */
    TBlog findBlogById(int id) throws Exception;

    /**
     * 通过当前博客id查询下一篇博客的id（将id小于当前id的所有博客升序排列取出第一条）
     * @param id
     * @return
     * @throws Exception
     */
    TBlog findNextBlogById(@Param("id") int id)throws Exception;


    /**
     * 通过当前博客id查询上一篇博客的id（将id小于当前id的所有博客降序排列取出第一条）
     * @param id
     * @return
     * @throws Exception
     */
    TBlog findPreBlogById(@Param("id") int id)throws Exception;
}

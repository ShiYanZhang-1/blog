package top.shiyana.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.shiyana.blog.pojo.entity.TBlog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
public interface ITBlogService extends IService<TBlog> {

    //查询博客日期和数量
    String findBlogDataAndCount() throws Exception;

    //查询博客分页详情
    IPage<TBlog> findBlogByPage(IPage<TBlog> page,TBlog blog) throws Exception;

    //通过Id查询博客信息
    TBlog findBlogById(int id) throws Exception;

    /**
     * 通过当前博客id查询上一篇博客的id（将id小于当前id的所有博客降序排列取出第一条）
     * @param id
     * @return
     * @throws Exception
     */
    TBlog findPreBlogById(int id) throws Exception;

    /**
     * 通过当前博客id查询下一篇博客的id（将id小于当前id的所有博客升序排列取出第一条）
     * @param id
     * @return
     * @throws Exception
     */
    TBlog findNextBlogById(int id) throws Exception;

    /**
     * 新增博客
     * @param blog
     * @return
     */
    int addBlog(TBlog blog) throws Exception;


}

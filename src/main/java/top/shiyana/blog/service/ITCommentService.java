package top.shiyana.blog.service;

import top.shiyana.blog.pojo.entity.TComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
public interface ITCommentService extends IService<TComment> {

    /**
     * 根据博客id查询该博客下的评论列表
     * @param id
     * @param state
     * @return
     * @throws Exception
     */
    List<TComment> findCommentByBlogId(int id, int state) throws Exception;


}

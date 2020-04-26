package top.shiyana.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.shiyana.blog.pojo.entity.TComment;
import top.shiyana.blog.mapper.TCommentMapper;
import top.shiyana.blog.service.ITCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
@Service
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements ITCommentService {


    @Resource
    private TCommentMapper commentMapper;

    @Override
    public List<TComment> findCommentByBlogId(int id, int state) throws Exception {
        QueryWrapper<TComment> queryWrapper = new QueryWrapper<TComment>();
        queryWrapper.eq("blogId",id);//博客id
        queryWrapper.eq("state",state);//审核状态
        return commentMapper.selectList(queryWrapper);
    }
}

package top.shiyana.blog.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.shiyana.blog.service.ITBlogtypeService;
import top.shiyana.blog.pojo.entity.TBlogtype;
import top.shiyana.blog.mapper.TBlogtypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.shiyana.blog.util.SysConstant;

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
public class TBlogtypeServiceImpl extends ServiceImpl<TBlogtypeMapper, TBlogtype> implements ITBlogtypeService {

    @Resource
    private TBlogtypeMapper tBlogtypeMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String getBlogTypeIdAndBlogCount() throws Exception {
        //从Redis缓存中读取博客分类信息
        String typeCount = redisTemplate.opsForValue().get(SysConstant.BLOG_TYPE_COUNT);
        //判读Redis缓存中是否存在分类信息的数据，如果是第一次查询就从数据库中查询，并且添加到Redis中
        //为空表示缓存中没有数控
        if(StringUtils.isEmpty(typeCount)){
            //查询就从数据库中查询，并且添加到Redis缓存中
            List<TBlogtype> blogTypeIdAndBlogCount = tBlogtypeMapper.getBlogTypeIdAndBlogCount();
            //将查询的集合的数据放到缓存中
            typeCount = JSON.toJSONString(blogTypeIdAndBlogCount);
//            System.out.println("+++++++++++++++使用Redis============");
            redisTemplate.opsForValue().set(SysConstant.BLOG_TYPE_COUNT,typeCount);
        }
        return typeCount;
    }
}

package top.shiyana.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.shiyana.blog.pojo.dto.BloggerDTO;
import top.shiyana.blog.pojo.entity.TBlogger;
import top.shiyana.blog.mapper.TBloggerMapper;
import top.shiyana.blog.service.ITBloggerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.shiyana.blog.util.RedisUtil;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
@Service
public class TBloggerServiceImpl extends ServiceImpl<TBloggerMapper, TBlogger> implements ITBloggerService {


    @Resource
    private TBloggerMapper bloggerMapper;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public TBlogger findBloggerByEmail(String email) throws Exception {
        QueryWrapper<TBlogger> queryWrapper = new QueryWrapper<TBlogger>();
        queryWrapper.eq("email",email);//参数1是数据库表中的列名
        TBlogger tBlogger = bloggerMapper.selectOne(queryWrapper);
        return tBlogger;
    }

    /**
     * 根据邮箱注册用户
     * @param blogger
     * @return
     */
    @Override
    public Boolean register(BloggerDTO blogger) {
        //从redis中获取改邮箱的验证码
        String loginCode = (String) redisUtil.get(blogger.getEmail() + ":login_code");
        if(loginCode!=null){
            if(blogger.getCode().equalsIgnoreCase(loginCode)){
                blogger.setNickName(blogger.getUserName());
                int insert = bloggerMapper.insert(blogger);
                if(insert == 1){
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }
}

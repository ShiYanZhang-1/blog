package top.shiyana.blog.util;

/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.util
 * @ClassName: SysConstant
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/13 14:41
 * @Version: 1.0
 */
public interface SysConstant {

    //评论的审核状态 2 审核通过 1 审核没通过
    int COMMENT_STATE_SUCCESS = 2;

    int COMMENT_STATE_FALSE = 1;

    //session中保存的角色key
    String LOGINUSER = "loginUser";

    /**
     * 缓存保存数据时间以及数量的Redis的key
     */
    String BLOG_DATA_COUNT = "blog_data_count";

    /**
     * 缓存保存数据类型以及数量的Redis的key
     */
    String BLOG_TYPE_COUNT = "blog_type_count";

    /**
     * ElasticSearch 索引的名字
     */
    String BLOG_ES_INDEX = "blog_index";





}

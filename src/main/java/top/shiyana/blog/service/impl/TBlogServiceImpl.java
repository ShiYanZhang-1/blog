package top.shiyana.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import top.shiyana.blog.service.ITBlogService;
import top.shiyana.blog.pojo.entity.TBlog;
import top.shiyana.blog.mapper.TBlogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.shiyana.blog.util.SysConstant;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
@Service
@Transactional
public class TBlogServiceImpl extends ServiceImpl<TBlogMapper, TBlog> implements ITBlogService {

    @Resource
    private TBlogMapper mapper;

    @Resource
    private RedisTemplate<String,String> redisTemplate;


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 查询博客日期和数量
     * @return
     * @throws Exception
     */
    @Override
    public String findBlogDataAndCount() throws Exception {

        //从Redis缓存中读取博客分类信息
        String typeCount = redisTemplate.opsForValue().get(SysConstant.BLOG_DATA_COUNT);
        //判读Redis缓存中是否存在分类信息的数据，如果是第一次查询就从数据库中查询，并且添加到Redis中
        //为空表示缓存中没有数控
        if(StringUtils.isEmpty(typeCount)){
            //查询就从数据库中查询，并且添加到Redis缓存中
            List<TBlog> blogTypeIdAndBlogCount = mapper.findBlogDataAndCount();
            //将查询的集合的数据放到缓存中
            typeCount = JSON.toJSONString(blogTypeIdAndBlogCount);
            redisTemplate.opsForValue().set(SysConstant.BLOG_DATA_COUNT,typeCount);
        }
        return typeCount;
    }

    @Override
    public IPage<TBlog> findBlogByPage(IPage<TBlog> page, TBlog blog) throws Exception {
        return mapper.findBlogByPage(page,blog);
    }

    //查明博客详情
    @Override
    public TBlog findBlogById(int id) throws Exception {
        //每点击一次就要将阅读次数  +1
        TBlog blog = mapper.findBlogById(id);
        //在原来的基础上将阅读次数 +1
        blog.setClickHit(blog.getClickHit()+1);
        //修改阅读次数
        mapper.updateById(blog);

        return blog;
    }

    /**
     * 通过当前博客id查询上一篇博客的id（将id小于当前id的所有博客降序排列取出第一条）
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public TBlog findPreBlogById(int id) throws Exception {
        return mapper.findPreBlogById(id);
    }

    /**
     * 通过当前博客id查询下一篇博客的id（将id小于当前id的所有博客升序排列取出第一条）
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public TBlog findNextBlogById(int id) throws Exception {
        return mapper.findNextBlogById(id);
    }

    @Override
    public int addBlog(TBlog blog) throws Exception {
        blog.setClickHit(0);
        blog.setReplyHit(0);
        blog.setReleaseDate(new Date());
        //清空之前的缓存
        redisTemplate.delete(SysConstant.BLOG_DATA_COUNT);
        redisTemplate.delete(SysConstant.BLOG_TYPE_COUNT);
        return mapper.insert(blog);
    }







    /**
     * 根据关键字分页查询博客
     * @param keyoWord
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public List<Map<String,Object>> highlightpage(String keyoWord,Integer page,Integer size) throws IOException {

        if(page<=0 || page == null){
            page = 1;
        }

        if(size<=0 || size == null){
            size = 5;
        }

        SearchRequest searchRequest = new SearchRequest(SysConstant.BLOG_ES_INDEX);
        //搜索条件构造
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title",keyoWord);
        //设置超时时间
        searchSourceBuilder.timeout(new TimeValue(2, TimeUnit.SECONDS));
        searchSourceBuilder.query(termQueryBuilder);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false); //是否开启多个字段高亮  只高亮第一个出现的
        highlightBuilder.preTags("<span style='color: red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        List<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap(); //原来的结果
            //判断高亮的字段是否为空
            if(title!=null){
                //不空就取出所有的高亮字段
                Text[] fragments = title.fragments();
                String n_title = "";
                for (Text fragment : fragments) {
                    //把遍历的高亮字段赋值给新的title
                    n_title += fragment;
                }
                //重新设置title字段
                sourceAsMap.put("title",n_title);
            }
            list.add(sourceAsMap);
        }
        return list;
    }

}

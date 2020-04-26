package top.shiyana.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author 誓言
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TBlog对象", description="")
public class TBlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @TableField(exist = false)
    private String releaseDateStr;

    //博客内容，无html标签。获取博客内容的纯文本
    @TableField(exist = false)
    private String contentNoTag;

    //属性在数据库不存在是查询的时候取得别名
    @TableField(exist = false)
    private String blogCount;

    //属性在数据库不存在是查询的时候取得别名
    @ApiModelProperty(value = "博客标题")
    private String title;

    @ApiModelProperty(value = "博客摘要信息")
    private String summary;

    @ApiModelProperty(value = "博客发布时间")
    @TableField("releaseDate")
    private Date releaseDate;

    @ApiModelProperty(value = "点击阅读量")
    @TableField("clickHit")
    private Integer clickHit;

    @ApiModelProperty(value = "评论数量")
    @TableField("replyHit")
    private Integer replyHit;

    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "博客所属分类")
    @TableField("typeId")
    private Integer typeId;

    @ApiModelProperty(value = "关键词")
    @TableField("keyWord")
    private String keyWord;

    //一对一查询映射结果字段
    @TableField(exist = false)
    private TBlogtype blogtype;

}

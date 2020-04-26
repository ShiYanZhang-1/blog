package top.shiyana.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 誓言
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TBlogtype对象", description="")
public class TBlogtype implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客类型ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    //每个博客分类下的数量
    @TableField(exist = false)
    private Integer blogCount;

    @ApiModelProperty(value = "博客分类名称")
    @TableField("typeName")
    private String typeName;

    @ApiModelProperty(value = "博客优先级排序")
    @TableField("orderNo")
    private Integer orderNo;


}

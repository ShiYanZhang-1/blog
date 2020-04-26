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
@ApiModel(value="TComment对象", description="")
public class TComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论编号
     */
    @ApiModelProperty(value = "评论编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @ApiModelProperty(value = "用户IP地址")
    @TableField("userIp")
    private String userIp;

    @ApiModelProperty(value = "所属博客id")
    @TableField("blogId")
    private Integer blogId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论时间")
    @TableField("commentDate")
    private Date commentDate;

    @ApiModelProperty(value = "审核状态1：待审核 2：已审核")
    private Integer state;


}

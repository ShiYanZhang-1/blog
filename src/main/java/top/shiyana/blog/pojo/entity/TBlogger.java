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
@ApiModel(value="TBlogger对象", description="")
public class TBlogger implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;


    @ApiModelProperty(value = "用户名")
    @TableField("userName")
    private String userName;


    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "个人简介")
    private String profile;

    @ApiModelProperty(value = "昵称")
    @TableField("nickName")
    private String nickName;

    @ApiModelProperty(value = "座右铭")
    private String sign;

    @ApiModelProperty(value = "博主头像")
    @TableField("imageName")
    private String imageName;

    @ApiModelProperty(value = "用户邮箱  也是登录账号")
    @TableField("email")
    private String email;


}

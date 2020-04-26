package top.shiyana.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.shiyana.blog.pojo.entity.TBlogger;

/**
 * @ProjectName: blog
 * @Package: top.shiyana.blog.pojo.dto
 * @ClassName: BloggerDTO
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/25 12:14
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloggerDTO extends TBlogger {

    private String code;
}

package top.shiyana.hospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: hospital
 * @Package: top.shiyana.hospital.vo
 * @ClassName: ResponseResult
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/20 15:57
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {

    private Integer code;

    private String message;

}

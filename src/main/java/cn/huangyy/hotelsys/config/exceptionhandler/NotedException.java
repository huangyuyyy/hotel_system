package cn.huangyy.hotelsys.config.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotedException extends RuntimeException{
    private Integer code;//状态码
    private String msg;//异常信息
}

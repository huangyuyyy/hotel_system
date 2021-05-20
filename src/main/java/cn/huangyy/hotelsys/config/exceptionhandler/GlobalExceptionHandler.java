package cn.huangyy.hotelsys.config.exceptionhandler;


import cn.huangyy.hotelsys.utils.DataResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public DataResult error(Exception e){
        e.printStackTrace();
        return DataResult.error().message("执行了全局异常处理！");
    }

    @ExceptionHandler(ArithmeticException.class)
    public DataResult error(ArithmeticException e){
        e.printStackTrace();
        return DataResult.error().message("执行了ArithmeticException异常处理！");
    }

    @ExceptionHandler(NotedException.class)
    public DataResult error(NotedException e){
        e.printStackTrace();
        return DataResult.error().code(e.getCode()).message(e.getMessage());
    }
}

package cn.huangyy.hotelsys.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DataResult {

    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data=new HashMap<>();

    private DataResult(){}

    public static DataResult ok(){
        DataResult result=new DataResult();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }

    public static DataResult error(){
        DataResult result=new DataResult();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        return result;
    }

    public DataResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public DataResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public DataResult message(String message){
        this.setMessage(message);
        return this;
    }

    public DataResult data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public DataResult data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

}

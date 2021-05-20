package cn.huangyy.hotelsys.config.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setInsertFieldValByName("gmtCreate",new Date(),metaObject);
        this.setInsertFieldValByName("gmtModified",new Date(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setInsertFieldValByName("gmtModified",new Date(),metaObject);
    }
}

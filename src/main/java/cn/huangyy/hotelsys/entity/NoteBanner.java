package cn.huangyy.hotelsys.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 首页banner表
 * </p>
 *
 * @author hyy
 * @since 2021-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class NoteBanner implements Serializable {

    private static final long serialVersionUID=1L;


      @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;


    private String title;


    private String imageUrl;


    private String linkUrl;


    private Integer sort;


    @TableLogic
    private Boolean isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

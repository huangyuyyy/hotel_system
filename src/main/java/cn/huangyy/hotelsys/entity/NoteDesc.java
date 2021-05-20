package cn.huangyy.hotelsys.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hyy
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NoteDesc implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String userId;

    private String dayId;

    private String date;

    private String title;

    private String description;

    private String noteDate;

    @TableLogic
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

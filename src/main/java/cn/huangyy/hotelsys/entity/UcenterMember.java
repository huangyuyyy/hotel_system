package cn.huangyy.hotelsys.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author hyy
 * @since 2021-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class UcenterMember implements Serializable {

    private static final long serialVersionUID=1L;


      @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;


    private String mobile;


    private String password;


    private String nickname;


    private Integer sex;


    private Integer age;


    private String avatar;


    private Integer isVip;


    private Boolean isDisabled;

    @TableLogic
    private Boolean isDeleted;


    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

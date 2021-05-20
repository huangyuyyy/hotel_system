package cn.huangyy.hotelsys.service;


import cn.huangyy.hotelsys.entity.UcenterMember;
import cn.huangyy.hotelsys.entity.vo.FindAllUserVo;
import cn.huangyy.hotelsys.entity.vo.MemberLoginVo;
import cn.huangyy.hotelsys.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author hyy
 * @since 2021-03-20
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(MemberLoginVo member);

    String register(RegisterVo registerVo);

    boolean updateInfoById(UcenterMember member);



    Map<String, Object> findAllUser(String id, long current, long limit, FindAllUserVo userVo);


}

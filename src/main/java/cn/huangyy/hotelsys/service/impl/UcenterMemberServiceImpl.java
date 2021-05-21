package cn.huangyy.hotelsys.service.impl;


import cn.huangyy.hotelsys.entity.UcenterMember;
import cn.huangyy.hotelsys.entity.vo.FindAllUserVo;
import cn.huangyy.hotelsys.entity.vo.MemberLoginVo;
import cn.huangyy.hotelsys.entity.vo.RegisterVo;

import cn.huangyy.hotelsys.mapper.UcenterMemberMapper;
import cn.huangyy.hotelsys.service.UcenterMemberService;
import cn.huangyy.hotelsys.utils.JwtUtils;
import cn.huangyy.hotelsys.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.util.*;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author hyy
 * @since 2021-03-20
 */
@Service
@Transactional
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public String login(MemberLoginVo member) {

        //获取登录手机号和密码
        String mobile=member.getMobile();
        String password=member.getPassword();
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password))
            return "登录失败！";
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        if (mobileMember==null)
            return "该账号暂未注册，请先注册！";

        //数据库中的密码加密后的，加密MD5
        if (!MD5.encrypt(password).equals(mobileMember.getPassword()))
            return "密码错误，请重试！";

        if (mobileMember.getIsDisabled())
            return "该账号已被冻结，请联系管理员！";

        return JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
    }

    @Override
    public String register(RegisterVo registerVo){
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String nickname = registerVo.getNickname();

        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)||StringUtils.isEmpty(nickname))
            return "有信息尚未填写，须填写完成注册！";

        //判断手机号是否存在
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count>0) return "该手机号已经注册，请勿重复注册！";

        UcenterMember ucenterMember=new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setNickname(nickname);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar("https://edu-hyy.oss-cn-guangzhou.aliyuncs.com/avatar/defaultAvatar.png");
        ucenterMember.setSex(3);
        baseMapper.insert(ucenterMember);
        return "";
    }

    @Override
    public boolean updateInfoById(UcenterMember member) {
        if (StringUtils.isEmpty(member.getAvatar()))
            member.setAvatar("https://edu-hyy.oss-cn-guangzhou.aliyuncs.com/avatar/defaultAvatar.png");
        int update = baseMapper.updateById(member);
        return update!=0;
    }

    @Override
    public Map<String, Object> findAllUser(String id, long current, long limit, FindAllUserVo userVo) {
        Map<String,Object> map=new HashMap<>();

        Integer style = userVo.getStyle();
        String mobile = userVo.getMobile();
        String nickname = userVo.getNickname();
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();

        //判断类型
        if (style==1) wrapper.eq("is_vip",1);
        else if (style==2) wrapper.eq("is_admin",1);
        else if (style==3) wrapper.eq("is_disabled",1);

        //判断是否存在
        if (!StringUtils.isEmpty(mobile)) wrapper.like("mobile",mobile);
        if (!StringUtils.isEmpty(nickname)) wrapper.like("nickname",nickname);

        wrapper.orderByDesc("gmt_create");
        Page<UcenterMember> page=new Page<>(current,limit);

        baseMapper.selectPage(page, wrapper);

        List<UcenterMember> records = page.getRecords();
        long total = page.getTotal();
        map.put("total",total);
        map.put("records",records);
        return map;
    }
}

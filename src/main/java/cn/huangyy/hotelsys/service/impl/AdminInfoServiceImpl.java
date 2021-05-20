package cn.huangyy.hotelsys.service.impl;

import cn.huangyy.hotelsys.entity.AdminInfo;
import cn.huangyy.hotelsys.mapper.AdminInfoMapper;
import cn.huangyy.hotelsys.service.AdminInfoService;
import cn.huangyy.hotelsys.utils.JwtUtils;
import cn.huangyy.hotelsys.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements AdminInfoService {
    @Override
    public String login(AdminInfo admin) {
        //获取登录手机号和密码
        String name= admin.getName();
        String password=admin.getPassword();
        if (StringUtils.isEmpty(name)||StringUtils.isEmpty(password))
            return "登录失败！";
        QueryWrapper<AdminInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("name",name);
        AdminInfo adminInfo = baseMapper.selectOne(wrapper);
        if (adminInfo==null)
            return "管理员账号错误，请重试！";

        //数据库中的密码加密后的，加密MD5
        if (!MD5.encrypt(password).equals(adminInfo.getPassword()))
            return "管理员密码错误，请重试！";

        return JwtUtils.getJwtToken(adminInfo.getId(), admin.getName());
    }
}

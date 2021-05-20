package cn.huangyy.hotelsys.service;

import cn.huangyy.hotelsys.entity.AdminInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminInfoService extends IService<AdminInfo> {
    String login(AdminInfo admin);
}

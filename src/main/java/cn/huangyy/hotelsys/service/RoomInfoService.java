package cn.huangyy.hotelsys.service;


import cn.huangyy.hotelsys.entity.RoomInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface RoomInfoService extends IService<RoomInfo> {
    Map<String, Object> getRoomList(long current, long limit, int type, int isFree);

    boolean addRoom(String name, int type,int window,int area);
}

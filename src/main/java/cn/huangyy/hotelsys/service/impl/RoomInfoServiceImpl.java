package cn.huangyy.hotelsys.service.impl;

import cn.huangyy.hotelsys.entity.RoomInfo;
import cn.huangyy.hotelsys.mapper.RoomInfoMapper;
import cn.huangyy.hotelsys.service.RoomInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo> implements RoomInfoService {

    @Override
    public Map<String, Object> getRoomList(long current, long limit, int type, int isFree,String name) {
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<RoomInfo> wrapper=new QueryWrapper<>();
        if (type==1) wrapper.eq("type",1);
        else if (type==2) wrapper.eq("type",2);
        else if (type==3) wrapper.eq("type",3);
        if (isFree==0) wrapper.eq("is_free",0);
        else if (isFree==1) wrapper.eq("is_free",1);
        if (!StringUtils.isEmpty(name)) wrapper.like("name",name);
        wrapper.orderByAsc("name");
        Page<RoomInfo> page=new Page<>(current,limit);
        baseMapper.selectPage(page, wrapper);
        List<RoomInfo> records = page.getRecords();
        long total = page.getTotal();
        map.put("total",total);
        map.put("records",records);
        return map;
    }

    @Override
    public boolean addRoom(String name, int type,int window,int area) {
        RoomInfo room=new RoomInfo();
        room.setName(name);
        room.setRoomWindow(window);
        room.setIsFree(1);
        room.setArea(area);
        room.setMemberId("0000");
        if (type==1){
            room.setType(1);
            room.setPrice(150);
            room.setImage("https://edu-hyy.oss-cn-guangzhou.aliyuncs.com/roomImg/room1.jpg");
        }else if (type==2){
            room.setType(2);
            room.setPrice(180);
            room.setImage("https://edu-hyy.oss-cn-guangzhou.aliyuncs.com/roomImg/room2.jpg");
        }else if (type==3){
            room.setType(3);
            room.setPrice(210);
            room.setImage("https://edu-hyy.oss-cn-guangzhou.aliyuncs.com/roomImg/room3.jpg");
        }
        int insert = baseMapper.insert(room);
        return insert!=0;
    }
}

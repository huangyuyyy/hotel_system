package cn.huangyy.hotelsys.service.impl;

import cn.huangyy.hotelsys.entity.RoomInfo;
import cn.huangyy.hotelsys.mapper.RoomInfoMapper;
import cn.huangyy.hotelsys.service.RoomInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo> implements RoomInfoService {

    @Override
    public Map<String, Object> getRoomList(long current, long limit, int type, int isFree) {
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<RoomInfo> wrapper=new QueryWrapper<>();
        if (type==1) wrapper.eq("type",1);
        else if (type==2) wrapper.eq("type",2);
        else if (type==3) wrapper.eq("type",3);
        if (isFree==0) wrapper.eq("is_free",0);
        else if (isFree==1) wrapper.eq("is_free",1);
        Page<RoomInfo> page=new Page<>(current,limit);
        baseMapper.selectPage(page, wrapper);
        List<RoomInfo> records = page.getRecords();
        long total = page.getTotal();
        map.put("total",total);
        map.put("records",records);
        return map;
    }
}

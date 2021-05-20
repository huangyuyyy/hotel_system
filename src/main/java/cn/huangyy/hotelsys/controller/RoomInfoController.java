package cn.huangyy.hotelsys.controller;

import cn.huangyy.hotelsys.entity.vo.FindAllUserVo;
import cn.huangyy.hotelsys.service.RoomInfoService;
import cn.huangyy.hotelsys.utils.DataResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/adminSys")
@CrossOrigin
public class RoomInfoController {

    @Resource
    private RoomInfoService roomService;

    @PostMapping("findAllUser/{current}/{limit}/{type}/{isFree}")
    public DataResult findAllUser(@PathVariable long current,
                                  @PathVariable long limit,
                                  @PathVariable int type,
                                  @PathVariable int isFree){
        Map<String,Object> map=roomService.getRoomList(current,limit,type,isFree);
        return DataResult.ok().data(map);
    }


}

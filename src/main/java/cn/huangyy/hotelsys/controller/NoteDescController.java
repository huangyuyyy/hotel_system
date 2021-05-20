package cn.huangyy.hotelsys.controller;


import cn.huangyy.hotelsys.entity.NoteDate;
import cn.huangyy.hotelsys.entity.NoteDesc;
import cn.huangyy.hotelsys.entity.vo.AddNoteVo1;
import cn.huangyy.hotelsys.entity.vo.FindAllVo;
import cn.huangyy.hotelsys.service.NoteDescService;
import cn.huangyy.hotelsys.utils.DataResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hyy
 * @since 2021-03-26
 */
@RestController
@RequestMapping("/usernote/note")
@CrossOrigin
public class NoteDescController {

    @Resource
    private NoteDescService descService;


    //添加事件
    @PostMapping("add/{userId}")
    public DataResult addNote(@PathVariable String userId,
                              @RequestBody AddNoteVo1 addVo) {
        boolean flag = descService.addNote1(userId, addVo);
        return flag ? DataResult.ok() : DataResult.error();
    }

    //查询所有，分页，条件
    @PostMapping("pageFind/{userId}/{current}/{limit}")
    public DataResult pageFind(@PathVariable long current,
                               @PathVariable long limit,
                               @PathVariable String userId,
                               @RequestBody(required = false) FindAllVo allVo) {

        Map<String, Object> map = descService.pageUserCondition(userId, current, limit, allVo);
        return DataResult.ok().data(map);
    }

    @PostMapping("pageFind2/{userId}/{current}/{limit}")
    public DataResult pageFind2(@PathVariable long current,
                                @PathVariable long limit,
                                @PathVariable String userId,
                                @RequestBody(required = false) FindAllVo allVo) {

        Map<String, Object> map = descService.pageUserCondition2(userId, current, limit, allVo);
        return DataResult.ok().data(map);
    }


    //首页查询记录日期与记录数
    @GetMapping({"getNoteList/{userId}/{current}/{limit}/{date}", "getNoteList/{userId}/{current}/{limit}"})
    public DataResult getNoteList(@PathVariable String userId,
                                  @PathVariable long current,
                                  @PathVariable long limit,
                                  @PathVariable(required = false) String date) {
        Map<String, Object> map = descService.getNoteList(userId, current, limit, date);
        return DataResult.ok().data(map);
    }

    //根据事件id查询事件
    @GetMapping("getOneNote/{id}")
    public DataResult getOneNote(@PathVariable String id) {
        NoteDesc noteDesc = descService.getById(id);
        return DataResult.ok().data("noteDesc", noteDesc);
    }

    //修改事件接口
    @PostMapping("updateNote")
    public DataResult updateNote(@RequestBody NoteDesc noteDesc) {
        System.out.println(noteDesc);
        boolean update = descService.updateById(noteDesc);
        return update ? DataResult.ok() : DataResult.error();
    }

    //根据id删除事件
    @DeleteMapping("deleteOneNote/{id}")
    public DataResult deleteOneNote(@PathVariable String id) {
        boolean remove = descService.removeById(id);
        return remove ? DataResult.ok() : DataResult.error();
    }

    //day分页查询
    @GetMapping({"getDayNote/{userId}/{date}/{current}/{limit}/{title}", "getDayNote/{userId}/{date}/{current}/{limit}"})
    public DataResult getDayList(@PathVariable String userId,
                                 @PathVariable String date,
                                 @PathVariable long current,
                                 @PathVariable long limit,
                                 @PathVariable(required = false) String title) {
        Map<String, Object> map=descService.getDayNote(userId, date,current, limit, title);
        return DataResult.ok().data(map);

    }

    //根据时间查寻时间信息
    @GetMapping("getDateInfo/{userId}/{date}")
    public DataResult getDateInfo(@PathVariable String userId,
                                  @PathVariable String date){
        NoteDate noteDate=descService.getDateInfo(userId,date);
        return DataResult.ok().data("noteDate",noteDate);
    }


    //更新每日备注
    @PostMapping("updateDateInfo")
    public DataResult updateDateInfo(@RequestBody NoteDate noteDate){
        boolean update=descService.updateDateInfo(noteDate);
        return update?DataResult.ok() : DataResult.error();
    }

    //得到首页的数据
    @GetMapping("getNoteInfo/{userId}/{noteDate}")
    public DataResult getNoteInfo(@PathVariable String userId,
                                  @PathVariable String noteDate){

        List<NoteDesc> list=descService.getNoteInfo(userId,noteDate);
        return DataResult.ok().data("list",list);
    }

    //删除一整天的记录
    @DeleteMapping("deleteDayData/{userId}/{date}")
    public DataResult deleteDayData(@PathVariable String userId,
                                  @PathVariable String date){
        boolean delete=descService.deleteDayData(userId,date);
        return delete?DataResult.ok() : DataResult.error();
    }

    //非vip的数
    @GetMapping("getNoVIP/{userId}")
    public DataResult getNoVIP(@PathVariable String userId){
        Integer count=descService.getNoVipCount(userId);
        return DataResult.ok().data("count",count);
    }


}









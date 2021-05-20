package cn.huangyy.hotelsys.service;



import cn.huangyy.hotelsys.entity.NoteDate;
import cn.huangyy.hotelsys.entity.NoteDesc;
import cn.huangyy.hotelsys.entity.vo.AddNoteVo1;
import cn.huangyy.hotelsys.entity.vo.FindAllVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyy
 * @since 2021-03-26
 */
public interface NoteDescService extends IService<NoteDesc> {

    boolean addNote1(String userId, AddNoteVo1 addVo);

    Map<String, Object> pageUserCondition(String userId,long current, long limit, FindAllVo allVo);

    Map<String, Object> getNoteList(String userId,long current, long limit,String date);

    Map<String, Object> pageUserCondition2(String userId, long current, long limit, FindAllVo allVo);

    Map<String, Object> getDayNote(String userId, String date,long current, long limit, String title);

    NoteDate getDateInfo(String userId, String date);

    boolean updateDateInfo(NoteDate noteDate);

    List<NoteDesc> getNoteInfo(String userId, String noteDate);

    boolean deleteDayData(String userId, String date);

    Integer getNoVipCount(String userId);
}

package cn.huangyy.hotelsys.service.impl;


import cn.huangyy.hotelsys.entity.NoteDate;
import cn.huangyy.hotelsys.entity.NoteDesc;
import cn.huangyy.hotelsys.entity.vo.AddNoteVo1;
import cn.huangyy.hotelsys.entity.vo.FindAllVo;
import cn.huangyy.hotelsys.entity.vo.GetListVo;
import cn.huangyy.hotelsys.mapper.NoteDateMapper;
import cn.huangyy.hotelsys.mapper.NoteDescMapper;
import cn.huangyy.hotelsys.service.NoteDescService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyy
 * @since 2021-03-26
 */
@Service
@Transactional
public class NoteDescServiceImpl extends ServiceImpl<NoteDescMapper, NoteDesc> implements NoteDescService {


    @Resource
    private NoteDateMapper dateMapper;

    @Resource
    private NoteDescMapper descMapper;

    @Override
    public boolean addNote1(String userId, AddNoteVo1 addVo) {
        NoteDesc noteDesc=new NoteDesc();
        noteDesc.setUserId(userId);
        noteDesc.setDayId(findDateId(addVo.getDate(),userId));
        noteDesc.setDate(addVo.getDate());
        noteDesc.setTitle(addVo.getTitle());
        noteDesc.setDescription(addVo.getDescription());
        noteDesc.setNoteDate(addVo.getNoteDate());
        int insert = descMapper.insert(noteDesc);

        return insert!=0;
    }

    @Override
    public Map<String, Object> pageUserCondition(String userId,long current, long limit, FindAllVo allVo) {
        Page<NoteDesc> pageNote=new Page<>(current,limit);

        QueryWrapper<NoteDesc> wrapper=new QueryWrapper<>();
        String date = allVo.getDate();
        String title = allVo.getTitle();
        String noteDate = allVo.getNoteDate();
        wrapper.eq("user_id",userId).orderByDesc("date").orderByDesc("gmt_modified");
        if (!StringUtils.isEmpty(date)) wrapper.eq("date",date);
        if (!StringUtils.isEmpty(noteDate)) wrapper.like("note_date",noteDate);
        if (!StringUtils.isEmpty(title)) wrapper.like("title",title);
        descMapper.selectPage(pageNote,wrapper);
        long total=pageNote.getTotal();
        List<NoteDesc> records=pageNote.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return map;
    }

    @Override
    public Map<String, Object> pageUserCondition2(String userId, long current, long limit, FindAllVo allVo) {
        Page<NoteDesc> pageNote=new Page<>(current,limit);
        QueryWrapper<NoteDesc> wrapper=new QueryWrapper<>();
        String date = allVo.getDate();
        String title = allVo.getTitle();
        String noteDate = allVo.getNoteDate();
        wrapper.eq("user_id",userId).orderByDesc("note_date").orderByDesc("gmt_modified");
        if (!StringUtils.isEmpty(date)) wrapper.eq("date",date);
        if (!StringUtils.isEmpty(noteDate)) wrapper.like("note_date",noteDate);
        if (!StringUtils.isEmpty(title)) wrapper.like("title",title);
        descMapper.selectPage(pageNote,wrapper);
        long total=pageNote.getTotal();
        List<NoteDesc> records=pageNote.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return map;
    }

    @Override
    public Map<String, Object> getDayNote(String userId, String date,long current, long limit, String title) {
        Page<NoteDesc> descPage=new Page<>(current,limit);
        QueryWrapper<NoteDesc> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("date",date).orderByDesc("gmt_modified");
        if (!StringUtils.isEmpty(title)) wrapper.like("title",title);
        descMapper.selectPage(descPage,wrapper);
        long total=descPage.getTotal();
        List<NoteDesc> records = descPage.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return map;
    }

    @Override
    public NoteDate getDateInfo(String userId, String date) {
        QueryWrapper<NoteDate> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("date",date);
        return dateMapper.selectOne(wrapper);
    }

    @Override
    public boolean updateDateInfo(NoteDate noteDate) {
        int i = dateMapper.updateById(noteDate);
        return i!=0;
    }

    @Override
    public List<NoteDesc> getNoteInfo(String userId, String noteDate) {
        QueryWrapper<NoteDesc> wrapper=new QueryWrapper<>();
        String beginDate=noteDate+" "+
        wrapper.eq("user_id",userId).like("note_date",noteDate).orderByDesc("note_date").last("limit 4");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public boolean deleteDayData(String userId, String date) {
        QueryWrapper<NoteDesc> wrapper1=new QueryWrapper<>();
        wrapper1.eq("user_id",userId).eq("date",date);
        int delete = baseMapper.delete(wrapper1);

        QueryWrapper<NoteDate> wrapper2=new QueryWrapper<>();
        wrapper2.eq("user_id",userId).eq("date",date);
        int delete1 = dateMapper.delete(wrapper2);
        return delete!=0&&delete1!=0;
    }

    @Override
    public Integer getNoVipCount(String userId) {
        QueryWrapper<NoteDesc> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return baseMapper.selectCount(wrapper);
    }

    @Override
    public Map<String, Object> getNoteList(String userId,long current, long limit,String date) {
        Map<String,Object> map=new HashMap<>();
        List<GetListVo> vos=new ArrayList<>();
        QueryWrapper<NoteDate> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId).orderByDesc("date");
        if (!StringUtils.isEmpty(date)){
            wrapper.eq("date",date);
            NoteDate noteDate = dateMapper.selectOne(wrapper);
            if (noteDate==null){
                map.put("total",0);
                map.put("vos",vos);
                return map;
            }
            QueryWrapper<NoteDesc> wrapper1=new QueryWrapper<>();
            wrapper1.eq("user_id",userId).eq("date",date);
            Integer count = baseMapper.selectCount(wrapper1);
            if (count==0){

            }
            GetListVo vo=new GetListVo();
            vo.setDate(date);
            vo.setTotal(count);
            vo.setRemark(noteDate.getRemark());
            vos.add(vo);
            map.put("total",1);
            map.put("vos",vos);
            return map;
        }
        Page<NoteDate> page=new Page<>(current,limit);
        dateMapper.selectPage(page,wrapper);
        for (NoteDate noteDate : page.getRecords()) {
            GetListVo vo=new GetListVo();
            QueryWrapper<NoteDesc> wrapper1=new QueryWrapper<>();
            wrapper1.eq("user_id",userId).eq("day_id",noteDate.getId());
            List<NoteDesc> noteDescs = baseMapper.selectList(wrapper1);
            if (noteDescs.size()==0){
                dateMapper.deleteById(noteDate.getId());
                continue;
            }
            vo.setDate(noteDate.getDate());
            vo.setTotal(noteDescs.size());
            vo.setRemark(noteDate.getRemark());
            vos.add(vo);
        }
        map.put("total",page.getTotal());
        map.put("vos",vos);
        return map;
    }




    private String findDateId(String date,String memberId){
        QueryWrapper<NoteDate> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",memberId).eq("date",date);
        NoteDate noteDate = dateMapper.selectOne(wrapper);
        if (noteDate!=null) return noteDate.getId();
        else {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String Today = sdf.format(new Date());
            NoteDate noteDate1=new NoteDate();
            noteDate1.setUserId(memberId);
            noteDate1.setDate(Today);
            dateMapper.insert(noteDate1);
            QueryWrapper<NoteDate> wrapper2=new QueryWrapper<>();
            wrapper2.eq("user_id",memberId).eq("date",noteDate1.getDate());
            NoteDate noteDate2 = dateMapper.selectOne(wrapper2);
            System.out.println(noteDate2);
            return noteDate2.getId();
        }
    }
}

package cn.huangyy.hotelsys.controller;



import cn.huangyy.hotelsys.entity.UcenterMember;
import cn.huangyy.hotelsys.entity.vo.FindAllUserVo;
import cn.huangyy.hotelsys.entity.vo.MemberLoginVo;
import cn.huangyy.hotelsys.entity.vo.RegisterVo;
import cn.huangyy.hotelsys.service.UcenterMemberService;
import cn.huangyy.hotelsys.utils.DataResult;
import cn.huangyy.hotelsys.utils.JwtUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author hyy
 * @since 2021-03-20
 */
@RestController
@RequestMapping("/member")
@CrossOrigin
public class UcenterMemberController {
    @Resource
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public DataResult loginUser(@RequestBody MemberLoginVo member){
        //返回token值，使用jwt生成
        String token=memberService.login(member);
        return DataResult.ok().data("token",token);
    }



    //注册
    @PostMapping("register")
    public DataResult registerUser(@RequestBody RegisterVo registerVo){
        String res = memberService.register(registerVo);
        return StringUtils.isEmpty(res)?DataResult.ok().message(""):DataResult.error().message(res);
    }

    //得到展示信息
    @GetMapping("getMemberInfo")
    public DataResult getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        return DataResult.ok().data("userInfo",member);
    }
    //根据id得到信息
    @GetMapping("getMemberInfoById/{id}")
    public DataResult getMemberInfo(@PathVariable String id){
        UcenterMember member = memberService.getById(id);
        return DataResult.ok().data("member",member);
    }

    //根据id修改
    @PostMapping("updateMemberInfo")
    public DataResult updateMemberInfo(@RequestBody UcenterMember member){
        boolean flag=memberService.updateInfoById(member);
        return flag?DataResult.ok():DataResult.error();
    }



    //获取全部用户
    @PostMapping("findAllUser/{id}/{current}/{limit}")
    public DataResult findAllUser(@PathVariable String id,
                                  @PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) FindAllUserVo allUserVo){
        Map<String,Object> map=memberService.findAllUser(id,current,limit,allUserVo);
        return DataResult.ok().data(map);
    }


}


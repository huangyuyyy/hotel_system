package cn.huangyy.hotelsys.controller;

import cn.huangyy.hotelsys.entity.AdminInfo;
import cn.huangyy.hotelsys.service.AdminInfoService;
import cn.huangyy.hotelsys.utils.DataResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/adminSys")
@CrossOrigin
public class AdminInfoController {

    @Resource
    private AdminInfoService adminService;

    @PostMapping("login")
    public DataResult loginUser(@RequestBody AdminInfo admin){
        //返回token值，使用jwt生成
        String token=adminService.login(admin);
        return DataResult.ok().data("token",token);
    }
}

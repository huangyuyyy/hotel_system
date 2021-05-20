package cn.huangyy.hotelsys.controller;


import cn.huangyy.hotelsys.service.OssService;
import cn.huangyy.hotelsys.utils.DataResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/noteoss/fileoss")
@CrossOrigin
public class OssController {

    @Resource
    private OssService ossService;

    @PostMapping("uploadOssFile")
    public DataResult uploadOssFile(MultipartFile file){
        String url=ossService.uploadFileAvatar(file);
        return StringUtils.isEmpty(url)?DataResult.error():DataResult.ok().data("url",url);
    }

    @GetMapping("deleteOssFile/{url}")
    public DataResult deleteOssFile(@PathVariable String url){
        System.out.println(url);
        url=url.replaceAll("-","/");
        boolean flag=ossService.deleteFileAvatar(url);
        return flag?DataResult.ok():DataResult.error();
    }

    @PostMapping("uploadBanner")
    public DataResult uploadBanner(MultipartFile file){
        String url=ossService.uploadBanner(file);
        return StringUtils.isEmpty(url)?DataResult.error():DataResult.ok().data("url",url);
    }

    @PostMapping("uploadFunction")
    public DataResult uploadFunction(MultipartFile file){
        String url=ossService.uploadFunction(file);
        return StringUtils.isEmpty(url)?DataResult.error():DataResult.ok().data("url",url);
    }




}

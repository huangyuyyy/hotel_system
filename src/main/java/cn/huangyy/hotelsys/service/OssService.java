package cn.huangyy.hotelsys.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFileAvatar(MultipartFile file);

    boolean deleteFileAvatar(String url);

    String uploadBanner(MultipartFile file);

    String uploadFunction(MultipartFile file);
}

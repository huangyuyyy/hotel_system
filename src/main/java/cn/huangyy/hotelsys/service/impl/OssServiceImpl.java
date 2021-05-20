package cn.huangyy.hotelsys.service.impl;


import cn.huangyy.hotelsys.service.OssService;
import cn.huangyy.hotelsys.utils.ConstantUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantUtils.KEY_ID;
        String accessKeySecret = ConstantUtils.KEY_SECRET;
        String bucketName = ConstantUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = null;
        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = null;
        try {
            ossClient=new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            inputStream=file.getInputStream();

            String filename = UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
            String data = sdf.format(new Date());
            filename="avatar/"+data+"/"+filename;

            ossClient.putObject(bucketName, filename, inputStream);

            //https://edu-hyy.oss-cn-guangzhou.aliyuncs.com/1.jpg
            return "https://"+bucketName+"."+endpoint+"/"+filename;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteFileAvatar(String url) {
        String endpoint = ConstantUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantUtils.KEY_ID;
        String accessKeySecret = ConstantUtils.KEY_SECRET;
        String bucketName = ConstantUtils.BUCKET_NAME;
        System.out.println(url);
    // 创建OSSClient实例。
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            ossClient.deleteObject(bucketName, url);
            System.out.println(1111);
            // 关闭OSSClient。
            ossClient.shutdown();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String uploadBanner(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantUtils.KEY_ID;
        String accessKeySecret = ConstantUtils.KEY_SECRET;
        String bucketName = ConstantUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = null;
        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = null;
        try {
            ossClient=new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            inputStream=file.getInputStream();

            String filename = UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();

            filename="banner/"+filename;

            ossClient.putObject(bucketName, filename, inputStream);

            //https://edu-hyy.oss-cn-guangzhou.aliyuncs.com/1.jpg
            return "https://"+bucketName+"."+endpoint+"/"+filename;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String uploadFunction(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantUtils.KEY_ID;
        String accessKeySecret = ConstantUtils.KEY_SECRET;
        String bucketName = ConstantUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = null;
        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = null;
        try {
            ossClient=new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            inputStream=file.getInputStream();

            String filename = UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();

            filename="functionImg/"+filename;

            ossClient.putObject(bucketName, filename, inputStream);

            //https://edu-hyy.oss-cn-guangzhou.aliyuncs.com/1.jpg
            return "https://"+bucketName+"."+endpoint+"/"+filename;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

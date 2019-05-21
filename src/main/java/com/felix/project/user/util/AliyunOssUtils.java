package com.felix.project.user.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.felix.project.commonConfig.util.UUIDUtils;
import com.felix.project.user.controller.UserController;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * @ClassName AliyunOssUtils
 * @Description TODO
 * @Author fangyong
 * @Date 2019/5/14 10:16
 **/
@Configuration
public class AliyunOssUtils {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    AliyunOssProperties aliyunOssProperties;

    /** 上传文件*/
    public  String upLoad(File file){

        String endpoint = aliyunOssProperties.getEndpoint();
        System.out.println("获取到的Point为:"+endpoint);
        String accessKeyId = aliyunOssProperties.getAccessId();
        String accessKeySecret = aliyunOssProperties.getAccessKey();
        String bucketName = aliyunOssProperties.getBucket();
        String fileHost = aliyunOssProperties.getDir();

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        // 判断文件
        if(file==null){
            return null;
        }
        String fileUrl = "";
        OSSClient client=new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 判断容器是否存在,不存在就创建
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            fileUrl = fileHost + (UUIDUtils.getUUID() + file.getName().substring(file.getName().lastIndexOf("."),file.getName().length()));
            // 上传文件
            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            // 设置权限(公开读)
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {

                logger.info("------OSS文件上传成功------" + fileUrl);
            }

        }catch (OSSException oe){
            logger.error(oe.getMessage());
        }catch (ClientException ce){
            logger.error(ce.getErrorMessage());
        }finally{
            if(client!=null){
                client.shutdown();
            }
        }
        return fileUrl;
    }
}

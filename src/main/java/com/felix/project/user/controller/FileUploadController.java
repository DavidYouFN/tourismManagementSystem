package com.felix.project.user.controller;

import com.felix.project.commodity.service.CommodityService;
import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.StaticProperties;
import com.felix.project.user.util.AliyunOssUtils;
import com.felix.project.user.util.DeleteFileUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName FileUpload1Controller
 * @Description TODO
 * @Author fangyong
 * @Date 2019/5/14 10:35
 **/
@RestController
@RequestMapping("/upload")
public class FileUploadController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    private static final String TO_PATH = "https://group-manage.oss-cn-shanghai.aliyuncs.com/";

    @Resource
    private AliyunOssUtils aliyunOSSUtil;

    @Resource
    CommodityService commodityService;

    @GetMapping("/toUpLoadFile")
    public String toUpLoadFile() {
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,TO_PATH);
    }

    /** 文件上传*/
    @PostMapping(value = "/uploadFile")
    public String uploadBlog(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        logger.info("文件上传");
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        String uploadUrl = "";
        try {

            if (file!=null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    uploadUrl = aliyunOSSUtil.upLoad(newFile);

                    System.err.println(uploadUrl);

                    // 删除上传的文件
                    File file1=new File("");
                    String s = file1.getAbsolutePath();
                    DeleteFileUtil.delete(s + "\\" + filename);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String ImgURL = TO_PATH+uploadUrl;
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,ImgURL);
    }
}

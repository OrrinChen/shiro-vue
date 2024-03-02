package com.wusuowei.shiro_jwt.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wusuowei.shiro_jwt.mapper.FilesMapper;
import com.wusuowei.shiro_jwt.model.po.Files;
import com.wusuowei.shiro_jwt.utils.DateUtil;
import com.wusuowei.shiro_jwt.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FilesMapper fileMapper;


    @Value("${file.upload.path}")
    private String fileUploadPath;

    @RequiresAuthentication
    @PostMapping("/upload")
    public R uploadImage(@RequestParam MultipartFile file) throws Exception {
        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        Files dbFiles = getFileByMd5(md5);
        if(dbFiles != null){
            url = dbFiles.getUrl();
        }else{

            String filename = file.getOriginalFilename();
            long size = file.getSize();
            String suffix = filename.substring(filename.lastIndexOf("."));

            String fileUUID =  DateUtil.getCurrentDateStr() +suffix;
            String pFilePath = fileUploadPath +DateUtil.getCurrentDatePath();
            String uriFile =  DateUtil.getCurrentDatePath()+fileUUID;
            File pFile = new File(pFilePath);
            if(!pFile.exists()){
                pFile.mkdirs();
            }
            String nowPath = pFilePath + fileUUID;
            File imageFile = new File(nowPath);
            // 上传文件到磁盘
            file.transferTo(imageFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://localhost:9090/files/" + uriFile;
            // 存储数据库
            Files saveFile = new Files();
            saveFile.setName(filename);
            saveFile.setType(suffix.replace(".",""));
            saveFile.setSize(size/1024);
            saveFile.setUrl(url);
            saveFile.setMd5(md5);
            fileMapper.insert(saveFile);
        }
        return R.ok().setData(url);
    }

    /**
     * @description 通过md5获取文件
     * @param md5 md5型
     * @return {@link Files }
     * @author LGY
     * @date 2023/03/27 13:37
     */
    private Files getFileByMd5(String md5) {
        // 查询文件的md5是否存在
        LambdaQueryWrapper<Files> queryWrapper = new LambdaQueryWrapper<Files>();
        queryWrapper.eq(Files::getMd5, md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }

}

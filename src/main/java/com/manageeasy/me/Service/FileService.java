package com.manageeasy.me.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    //文件上传
    public String addFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String oldFileName = file.getOriginalFilename();
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/upload/pic";
            String randomStr = UUID.randomUUID().toString();
            String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            File newFile = new File(path, newFileName);
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();//建立多级文件夹
            }
            file.transferTo(newFile);
            return (path + "/" + newFileName);
        }
        return "";
    }
}

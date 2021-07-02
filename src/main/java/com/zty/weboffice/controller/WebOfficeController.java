package com.zty.weboffice.controller;

import client.UaaAppConfigClient;
import com.zty.weboffice.dto.BasicAuthenticationInfo;
import com.zty.weboffice.dto.CheckAuthenticationInfo;
import com.zty.weboffice.service.DocumentOperation;
import com.zty.weboffice.utils.Sign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import utils.IResult;

import java.util.HashMap;

@RestController
@Slf4j
public class WebOfficeController {

    @Autowired
    private RestTemplate RestTemplate;

    @Autowired
    private DocumentOperation documentOperation;

    @Autowired
    private Sign sign;

    @Autowired
    private BasicAuthenticationInfo basicInfo;

    @Autowired
    private CheckAuthenticationInfo checkInfo;

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        IResult<String> stringIResult = sign.generateSign(basicInfo.getAppId(), basicInfo.getAppKey(), new HashMap<>());
        if (file.isEmpty()) {
            return "请上传文件";
        }
        String message = documentOperation.uploadDocument(basicInfo.getAppId(), stringIResult.getData(), file);
        return message;
    }


    @PostMapping("/checkFile")
    public String editFile(HashMap<String,String[]> map) {

        map.put("fileVersionId",new String[]{"617750031633485825_0"});
        IResult<String> result = sign.generateSign(checkInfo.getAppId(), checkInfo.getAppKey(), map);
        System.out.println(result.getData());
        String message = result.getData();
        return message;
    }

    @PostMapping("/test")
    public String test(MultipartFile file){
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        return "end";
    }
}

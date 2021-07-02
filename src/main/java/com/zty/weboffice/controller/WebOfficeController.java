package com.zty.weboffice.controller;

import client.UaaAppConfigClient;
import com.zty.weboffice.service.DocumentOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import utils.IResult;

import java.util.HashMap;

@RestController
@Slf4j
public class WebOfficeController {
    @Autowired
    private UaaAppConfigClient uaaAppConfigClient;

    @Autowired
    private RestTemplate RestTemplate;

    @Autowired
    private DocumentOperation documentOperation;

    @Value("${sign.appid}")
    private String appId;

    @Value("${sign.appkey}")
    private String appKey;

    @PostMapping("/upload")
    public String upload(@RequestBody MultipartFile file) {
        IResult<String> result = uaaAppConfigClient.generateSign(appId, appKey, new HashMap<>());
        String message = result.getData();
        if (file.isEmpty()){
            return "请上传文件";
        }
        String s = documentOperation.uploadDocument(appId, result.getData(), file);
        return s;
    }

    @GetMapping("/dowmload")
    @ResponseBody
    public String download() {
        log.info("appid:" + appId + "appkey:" + appKey);
        IResult<String> result = uaaAppConfigClient.generateSign(appId, appKey, new HashMap<>());
        String message = result.getData();
        return message;
    }

    @GetMapping("/editFile")
    public String editFile() {
        IResult<String> result = uaaAppConfigClient.generateSign(appId, appKey, new HashMap<>());
        String message = result.getData();
        return message;
    }
}

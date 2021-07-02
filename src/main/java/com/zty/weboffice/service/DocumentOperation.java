package com.zty.weboffice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zty
 */
public interface DocumentOperation {
    /**
     * 上传文档
     * @return
     * @param appId
     * @param sign
     * @param file
     */
    String uploadDocument(String appId, String sign, MultipartFile file);
}

package com.zty.weboffice.service.Impl;

import com.zty.weboffice.service.DocumentOperation;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

/**
 * @author zty
 */
@Service
public class DocumentOperationImpl implements DocumentOperation {
    @Override
    public String uploadDocument(String appId, String sign, MultipartFile file) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        String name = file.getName();
        RequestBody body = RequestBody.create(JSON, "wait");
        return "";
    }
}

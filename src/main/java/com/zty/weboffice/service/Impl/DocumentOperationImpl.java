package com.zty.weboffice.service.Impl;

import com.zty.weboffice.service.DocumentOperation;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        MultipartBody.Builder builder = new MultipartBody.Builder();
//        builder.setType(MediaType.parse("multipart/form-data"));

        builder.addFormDataPart("appId", appId);
        builder.addFormDataPart("sign", sign);
        try {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=" + file.getOriginalFilename())
                    , RequestBody.create(MediaType.parse("multipart/form-data"), file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        CommonsMultipartFile cf = (CommonsMultipartFile) file;
//        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        RequestBody body = builder.build();

        //发送请求
        Request request = new Request.Builder()
                .url("http://dmc.yozocloud.cn/api/file/upload")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            // 尝试将返回值转换成字符串并返回
            System.out.println("==>返回结果: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

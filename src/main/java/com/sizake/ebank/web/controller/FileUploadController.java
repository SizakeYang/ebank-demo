package com.sizake.ebank.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {


    // 访问路径为：http://ip:port/context/fileUpload/single
    @GetMapping(value = "/toSingle")
    public String upload() {
        return "/SingleFileUpload";
    }

    // 访问路径为：http://ip:port/context/fileUpload/batch
    @GetMapping(value = "/toMulti")
    public String batchUpload() {
        return "/MultiFileUpload";
    }

    @PostMapping(value = "/single")
    @ResponseBody
    public String upload(@RequestParam("file") final MultipartFile file) {
        if (!file.isEmpty()) {
            // 这里只是简单例子，文件直接输出到项目路径下。
            // 有时需要在服务端判断文件大小和文件类型
            final String file_type = file.getContentType();
            //经过实验,只跟上传文件后缀有关而跟实际内容无关 -->
            // 可以考虑判断文件流的头部分判断文件类型，但不可靠 -->
            // 文件上传前跟客户端对其进行签名,服务端进行验签
            final Long file_size = file.getSize();

            //对上传的文件路径设置:(1)固定前缀;(2)过滤非法字符(如"//",".."等)
            try (final BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File("D:\\test\\" + file.getOriginalFilename())))) {

                out.write(file.getBytes());
                out.flush();

            } catch (final IOException e) {
                e.printStackTrace();
                return "file upload failed!";
            }
            return String.format("single file upload success!,file type is %s,file size is %s", file_type, file_size);
        } else {
            return "failed;file empty!";
        }
    }

    @PostMapping(value = "/multi")
    public @ResponseBody
    String batchUpload(final HttpServletRequest request) {
        final List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try (final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("D:\\test\\" + file.getOriginalFilename())))) {
                    stream.write(file.getBytes());
                    stream.flush();
                } catch (final Exception e) {
                    return "You failed to upload " + i + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + i + " because the file was empty.";
            }
        }
        return "upload successful";
    }


}

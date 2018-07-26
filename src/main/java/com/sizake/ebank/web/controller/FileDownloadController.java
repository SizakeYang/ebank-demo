package com.sizake.ebank.web.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/fileDownload")
public class FileDownloadController {

    @GetMapping(value = "/responseEntity")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("fileName") final String fileName)
            throws IOException {
        final String filePath = "D:\\test\\" + fileName;
        final FileSystemResource file = new FileSystemResource(filePath);
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));

    }

    @RequestMapping(value = "/httpServletResponse")
    public void getDownload(@RequestParam("fileName") final String fileName, final HttpServletRequest request, final HttpServletResponse response) {

        // Get your file stream from wherever.
        final String fullPath = "D:\\test\\" + fileName;
        final File downloadFile = new File(fullPath);

        final ServletContext context = request.getServletContext();

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        final String headerKey = "Content-Disposition";
        final String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // Copy the stream to the response's output stream.
        try {
            final InputStream is = new FileInputStream(fullPath);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}

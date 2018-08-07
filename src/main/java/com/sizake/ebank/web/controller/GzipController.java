package com.sizake.ebank.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/gzip")
public class GzipController {


    // see the difference when http's header: {Accept-Encoding: gzip} is added
    @RequestMapping(value = "/getLargeJson", produces = "application/json")
    public String getLargeJson() throws IOException {
        return new String(Files.readAllBytes(Paths.get("D:/temp/huge.json")), StandardCharsets.UTF_8);
    }
}

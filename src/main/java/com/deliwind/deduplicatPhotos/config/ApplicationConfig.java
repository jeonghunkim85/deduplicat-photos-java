package com.deliwind.deduplicatPhotos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.File;

@Slf4j
@Configuration
public class ApplicationConfig {

    static final String DEFAULT_DIR = "./";

    public ApplicationConfig(@Value("${dir}") String startDir) {
        log.debug("start with {}", startDir);

        String dir = StringUtils.hasLength(startDir) ? startDir : DEFAULT_DIR;
        File f = new File(dir);

        if(!f.exists()) {
            throw new RuntimeException("input dir is not exists");
        }

        if(!f.isDirectory()) {
            throw new RuntimeException("input must be dir");
        }


    }
}

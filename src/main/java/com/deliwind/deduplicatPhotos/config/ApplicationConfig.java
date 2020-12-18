package com.deliwind.deduplicatPhotos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Slf4j
@Configuration
public class ApplicationConfig {

    static final String DEFAULT_DIR = "./";

    private final Set<String> exceptFileSet;

    public ApplicationConfig(
            @Value("${dir}") String startDir,
            @Value("${except.files}") List<String> exceptFileList
    ) {
        log.debug("start with {}", startDir);
        String dir = StringUtils.hasLength(startDir) ? startDir : DEFAULT_DIR;
        File f = new File(dir);
        log.debug("#path is {}", f.getAbsolutePath());
        if(!f.exists()) {
            throw new RuntimeException("input dir is not exists");
        }

        if(!f.isDirectory() || !f.canRead()) {
            throw new RuntimeException("input dir is not a directory or not readable");
        }

        this.exceptFileSet = exceptFileList.stream().collect(toSet());

        deduplicatPhotos(f);
    }

    public void deduplicatPhotos(File dir) {

        log.debug("except files {}", this.exceptFileSet);

        var filesList = Arrays.stream(dir.listFiles())
                .filter(file -> !this.exceptFileSet.contains(file.getName()))
                .collect(Collectors.toList());

    }
}

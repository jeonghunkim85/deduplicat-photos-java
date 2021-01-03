package com.deliwind.deduplicatPhotos;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.regex.Pattern;

@Getter
public class TargetFile {

    static Set TARGET_EXTS = Set.of("gif", "jpg", "heic", "mp4", "mov");

    private static final Pattern IMPORT_EXT_PATTERN = Pattern.compile("^(.*)(\\.[\\w]+)$");
    public static String getFileExt(String fileName) {
        String extWithDot = IMPORT_EXT_PATTERN.matcher(fileName).replaceAll("$2");
        if(!extWithDot.contains(".")) {
            return Strings.EMPTY;
        }
        return extWithDot.toLowerCase().substring(1);
    }

    private String path;
    private File file;

    public TargetFile(String path) throws FileNotFoundException {
        this.path = path;
        this.file = new File(path);
        if(!this.file.exists()) {
            throw new FileNotFoundException();
        }
        if(!this.file.canRead()) {
            throw new RuntimeException(String.format("cannot read the file %s", this.path));
        }
    }

    public boolean isTargetToCheck() {

        return true;
    }

    public String getHash() {
        return null;
    }

    public String getFileName() {
        return this.file.getName();
    }

    private String getFileExt() {
        return TargetFile.getFileExt(this.getFileName());
    }
}

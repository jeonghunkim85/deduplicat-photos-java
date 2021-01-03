package com.deliwind.deduplicatPhotos;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.of;

@Slf4j
@Nested
@DisplayName("TargetFileTest")
class TargetFileTest {

    String path = "./src/test/resources/photos/1.txt";
    File f = new File(path);
    TargetFile tf;

    @BeforeEach
    public void before() throws Exception {
        log.debug("#f.absolutePath: {}", f.getAbsolutePath());
        log.debug("#f.exists: {}", f.exists());
        TargetFile tf = new TargetFile(path);
    }

    @Test
    public void getFileNameTest() {
        String filename = tf.getFileName();
        assertThat(filename, is("1.txt"));
    }

    @Nested
    @DisplayName("파일 확장자 가져오는 getFileExt 메소드를 테스트 한다.")
    static class GetFileExtTest {

        @ParameterizedTest
        @MethodSource("getFileExtTestCases")
        public void getFileExtTest(String fileName, String expectedExt) {
            assertThat(TargetFile.getFileExt(fileName), is(expectedExt));
        }

        private static Stream<Arguments> getFileExtTestCases() { // argument source method
            return Stream.of(
                    of("1.txt", "txt"),
                    of("1.TXT", "txt"),
                    of("1.sometext.txt", "txt"),
                    of("some.file.name.mp4", "mp4"),
                    of("thereisnoext", "")
            );
        }
    }

    @Nested
    @DisplayName("당 파일이 체크 대상 파일인지 테스트 한다")
    static class IsTargetToCheckTest {

        @ParameterizedTest
        @MethodSource("isTargetToCheckTestCases")
        public void isTargetToCheckTest(String path, boolean expect) throws Exception {
            var targetFile = new TargetFile(path);
            var isTargetToCheck = targetFile.isTargetToCheck();

            assertThat(isTargetToCheck, is(expect));
        }

        public static Stream<Arguments> isTargetToCheckTestCases() {
            return Stream.of(
                    of("./src/test/resources/photos/image1.jpeg", true),
                    of("./src/test/resources/photos/1.txt", false)
            );
        }

    }
}
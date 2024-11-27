package com.ll.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilTest {
    @Test
    @DisplayName("파일을 생성할 수 있다.")
    public void t1() {
        // given
        String filePath = "test.txt";

        // when
        Util.file.touch(filePath);

        // then
        assertThat(
                Util.file.exists(filePath)
        ).isTrue();
    }

    @Test
    @DisplayName("파일의 내용을 수정할 수 있고, 읽을 수 있다.")
    public void t2() {
        // given
        String filePath = "test.txt";

        // when
        Util.file.set(filePath, "내용");

        // then
        assertThat(
                Util.file.get(filePath, "")
        ).isEqualTo("내용");
    }

    @Test
    @DisplayName("파일을 삭제할 수 있다.")
    public void t3() {
        // given
        String filePath = "test.txt";

        // when
        Util.file.touch(filePath);
        Util.file.delete(filePath);

        // then
        assertThat(
                Util.file.notExists(filePath)
        ).isTrue();
    }
}

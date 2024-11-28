package com.ll;

import com.ll.domain.wiseSaying.repository.WiseSayingFileRepository;
import com.ll.global.app.App;
import com.ll.global.app.AppConfig;
import com.ll.standard.util.TestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @BeforeAll
    public static void beforeAll() {
        AppConfig.setTestMode();
    }

    @Test
    @DisplayName("== 명언 앱 ==")
    public void t1() {
        String output = AppTest.run("");

        assertThat(output)
                .contains("== 명언 앱 ==");
    }

    @Test
    @DisplayName("명령) ")
    public void t2() {
        String output = AppTest.run("""
                목록
                """);

        assertThat(output)
                .contains("명령) ");
    }

    @Test
    @DisplayName("명령을 2번 이상 입력할 수 있습니다.")
    public void t3() {
        String output = AppTest.run("""
                목록
                목록
                """);

        String[] split = output.split("명령\\)");
        assertThat(split).hasSize(4);
    }

    public static String run(String input) {
        input = input.stripIndent().trim() + "\n종료";
        Scanner scanner = TestUtil.getScanner(input);
        ByteArrayOutputStream outputStream = TestUtil.setOutToByteArray();

        App app = new App(scanner);
        app.run();

        scanner.close();

        String output = outputStream.toString();

        TestUtil.clearSetOutToByteArray(outputStream);

        return output;
    }

    public static void dropTables() {
        WiseSayingFileRepository.dropTable();
    }

    public static void makeSampleData(int items) {
        App app = new App(null);
        app.makeSampleData(items);
    }
}

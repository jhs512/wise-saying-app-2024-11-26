package com.ll.domain.wiseSaying.controller;

import com.ll.AppTest;
import com.ll.global.app.AppConfig;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;


public class WiseSayingControllerTest {
    @BeforeAll
    public static void beforeAll() {
        AppConfig.setTestMode();
    }

    @BeforeEach
    public void beforeEach() {
        AppTest.dropTables();
    }

    @AfterEach
    public void afterEach() {
        AppTest.dropTables();
    }

    @Test
    @DisplayName("등록을 입력하면 내용과 작가를 입력받는다.")
    public void t4() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(output)
                .contains("명언 : ")
                .contains("작가 : ");
    }

    @Test
    @DisplayName("등록이 완료되면 명언번호가 출력된다.")
    public void t5() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(output)
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("매번 생성되는 명언번호는 1씩 증가 한다.")
    public void t6() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(output)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.")
                .contains("3번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록 명령어 : 입력된 명언들을 출력한다.")
    public void t7() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                등록
                나의 죽음을 적들에게 알리지 말라!
                이순신
                목록
                """);

        assertThat(output)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("3 / 이순신 / 나의 죽음을 적들에게 알리지 말라!")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("삭제 명령어 : 입력한 번호에 해당하는 명언이 삭제된다.")
    public void t8() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                목록
                """);

        assertThat(output)
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("삭제 명령어 : 존재하지 않는 명언번호에 대한 처리")
    public void t9() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=3
                목록
                """);

        assertThat(output)
                .contains("3번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("수정 명령어 : 기존 명언과 기존 작가를 보여준다.")
    public void t10() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                수정?id=2
                새 명언 내용
                새 작가
                """);

        assertThat(output)
                .contains("명언(기존) : 과거에 집착하지 마라.")
                .contains("작가(기존) : 작자미상");
    }

    @Test
    @DisplayName("수정 명령어 : 명언이 수정된다.")
    public void t11() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                수정?id=2
                현재와 자신을 사랑하라.
                홍길동
                목록
                """);

        assertThat(output)
                .contains("2 / 홍길동 / 현재와 자신을 사랑하라.");
    }

    @Test
    @DisplayName("빌드 명령어 : data.json 생성")
    public void t12() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                빌드
                """);

        assertThat(output)
                .contains("data.json 파일의 내용이 갱신되었습니다.");
    }

    @Test
    @DisplayName("목록(검색)")
    public void t13() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록?keywordType=content&keyword=과거
                """);

        assertThat(output)
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.");
    }

    @Test
    @DisplayName("목록(페이징) : 샘플데이터 생성")
    public void t14() {
        AppTest.makeSampleData(10);

        String output = AppTest.run("""
                목록
                """);

        assertThat(output)
                .contains("1 / 작자미상 / 명언 1")
                .contains("10 / 작자미상 / 명언 10");
    }
}


package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.global.app.AppConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingDbRepositoryTest {
    private static WiseSayingDbRepository wiseSayingRepository;

    @BeforeAll
    public static void beforeAll() {
        AppConfig.setTestMode();
        wiseSayingRepository = new WiseSayingDbRepository();

        wiseSayingRepository.dropTable();
        wiseSayingRepository.createTable();
    }

    @BeforeEach
    public void beforeEach() {
        wiseSayingRepository.truncateTable();
    }

    @Test
    public void t0() {

    }

    @Test
    @DisplayName("명언 저장")
    public void t1() {
        WiseSaying wiseSaying = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying);

        Optional<WiseSaying> opWiseSaying = wiseSayingRepository.findById(wiseSaying.getId());

        assertThat(
                opWiseSaying.get()
        ).isEqualTo(wiseSaying);
    }
}

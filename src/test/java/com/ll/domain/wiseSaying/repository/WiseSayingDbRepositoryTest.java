package com.ll.domain.wiseSaying.repository;

import com.ll.global.app.AppConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}

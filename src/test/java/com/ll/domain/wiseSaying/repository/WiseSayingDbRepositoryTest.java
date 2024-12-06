package com.ll.domain.wiseSaying.repository;

import com.ll.global.app.AppConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class WiseSayingDbRepositoryTest {
    private final WiseSayingDbRepository wiseSayingRepository = new WiseSayingDbRepository();

    @BeforeAll
    public static void beforeAll() {
        AppConfig.setTestMode();

        WiseSayingDbRepository.dropTable();
        WiseSayingDbRepository.createTable();
    }

    @BeforeEach
    public void beforeEach() {
        WiseSayingDbRepository.truncateTable();
    }
}

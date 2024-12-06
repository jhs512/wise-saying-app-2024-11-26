package com.ll.domain.wiseSaying.repository;

import com.ll.global.app.AppConfig;
import com.ll.standard.simpleDb.SimpleDb;

public class WiseSayingDbRepository {
    private final SimpleDb simpleDb;

    public WiseSayingDbRepository() {
        simpleDb = new SimpleDb(
                "localhost",
                "root",
                "lldj123414",
                AppConfig.getDbName()
        );
    }

    public void dropTable() {
        simpleDb.run(
                """
                        DROP TABLE IF EXISTS wiseSaying
                        """
        );
    }

    public void createTable() {
        simpleDb.run(
                """
                        CREATE TABLE wiseSaying (
                            id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                            content CHAR(100) NOT NULL,
                            author CHAR(100) NOT NULL
                        );
                        """
        );
    }

    public void truncateTable() {
        simpleDb.run(
                """
                        TRUNCATE TABLE wiseSaying
                        """
        );
    }
}

package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.global.app.AppConfig;
import com.ll.standard.simpleDb.SimpleDb;
import com.ll.standard.simpleDb.Sql;

import java.util.Optional;

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

    public WiseSaying save(WiseSaying wiseSaying) {
        Sql sql = simpleDb.genSql();

        sql.append("INSERT INTO wiseSaying")
                .append("SET content = ?", wiseSaying.getContent())
                .append(", author = ?", wiseSaying.getAuthor());

        int id = (int) sql.insert();

        wiseSaying.setId(id);

        return wiseSaying;
    }

    public Optional<WiseSaying> findById(int id) {
        Sql sql = simpleDb.genSql();

        sql.append("SELECT * FROM wiseSaying")
                .append("WHERE id = ?", id);

        WiseSaying wiseSaying = sql.selectRow(WiseSaying.class);

        if (wiseSaying == null) {
            return Optional.empty();
        }

        return Optional.of(wiseSaying);
    }

    public boolean deleteById(int id) {
        Sql sql = simpleDb.genSql();

        sql.append("DELETE FROM wiseSaying")
                .append("WHERE id = ?", id);

        return sql.delete() > 0;
    }
}

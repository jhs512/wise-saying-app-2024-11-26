package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.global.app.AppConfig;
import com.ll.standard.simpleDb.SimpleDb;
import com.ll.standard.simpleDb.Sql;
import com.ll.standard.util.Util;

import java.util.List;
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

        if (wiseSaying.isNew()) {
            sql.append("INSERT INTO wiseSaying")
                    .append("SET content = ?", wiseSaying.getContent())
                    .append(", author = ?", wiseSaying.getAuthor());

            int id = (int) sql.insert();

            wiseSaying.setId(id);
        } else {
            sql.append("UPDATE wiseSaying")
                    .append("SET content = ?", wiseSaying.getContent())
                    .append(", author = ?", wiseSaying.getAuthor())
                    .append("WHERE id = ?", wiseSaying.getId());

            sql.update();
        }

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

    public List<WiseSaying> findAll() {
        Sql sql = simpleDb.genSql();

        sql.append("SELECT * FROM wiseSaying")
                .append("ORDER BY id DESC");

        return sql.selectRows(WiseSaying.class);
    }

    public List<WiseSaying> findAllByOrerByIdAsc() {
        Sql sql = simpleDb.genSql();

        sql.append("SELECT * FROM wiseSaying")
                .append("ORDER BY id ASC");

        return sql.selectRows(WiseSaying.class);
    }

    public void archive(String archiveDirPath) {
        String jsonStr = Util.json.toString(
                findAllByOrerByIdAsc()
                        .stream()
                        .map(WiseSaying::toMap)
                        .toList()
        );

        Util.file.set(archiveDirPath, jsonStr);
    }

    public int count() {
        Sql sql = simpleDb.genSql();

        sql.append("SELECT COUNT(*) FROM wiseSaying");

        return (int) sql.selectLong();
    }
}

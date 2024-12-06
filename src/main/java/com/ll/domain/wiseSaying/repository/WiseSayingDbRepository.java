package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.global.app.AppConfig;
import com.ll.standard.dto.Pageable;
import com.ll.standard.simpleDb.SimpleDb;
import com.ll.standard.simpleDb.Sql;
import com.ll.standard.util.Util;

import java.util.List;
import java.util.Optional;

public class WiseSayingDbRepository implements WiseSayingRepository {
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

    @Override
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

    @Override
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

    @Override
    public boolean deleteById(int id) {
        Sql sql = simpleDb.genSql();

        sql.append("DELETE FROM wiseSaying")
                .append("WHERE id = ?", id);

        return sql.delete() > 0;
    }

    @Override
    public List<WiseSaying> findAll() {
        Sql sql = simpleDb.genSql();

        sql.append("SELECT * FROM wiseSaying")
                .append("ORDER BY id DESC");

        return sql.selectRows(WiseSaying.class);
    }

    public List<WiseSaying> findAllByOrderByIdAsc() {
        Sql sql = simpleDb.genSql();

        sql.append("SELECT * FROM wiseSaying")
                .append("ORDER BY id ASC");

        return sql.selectRows(WiseSaying.class);
    }

    @Override
    public void archive(String archiveDirPath) {
        String jsonStr = Util.json.toString(
                findAllByOrderByIdAsc()
                        .stream()
                        .map(WiseSaying::toMap)
                        .toList()
        );

        Util.file.set(archiveDirPath, jsonStr);
    }

    @Override
    public List<WiseSaying> findByKeyword(String keywordType, String keyword) {
        Sql sql = simpleDb.genSql();

        sql.append("SELECT * FROM wiseSaying");

        switch (keywordType) {
            case "content":
                sql.append("WHERE content LIKE ?", "%" + keyword + "%");
                break;
            case "author":
                sql.append("WHERE author LIKE ?", "%" + keyword + "%");
                break;
        }

        sql.append("ORDER BY id DESC");

        return sql.selectRows(WiseSaying.class);
    }

    @Override
    public void makeSampleData(int items) {
        for (int i = 1; i <= items; i++) {
            save(new WiseSaying(0, "명언 " + i, "작자미상"));
        }
    }

    @Override
    public int count() {
        Sql sql = simpleDb.genSql();

        sql.append("SELECT COUNT(*) FROM wiseSaying");

        return (int) sql.selectLong();
    }

    @Override
    public Pageable<WiseSaying> pageableAll(int itemsPerPage, int page) {
        int totalItems = count();

        Sql sql = simpleDb.genSql();

        sql.append("SELECT * FROM wiseSaying")
                .append("ORDER BY id DESC")
                .append("LIMIT ?, ?", (page - 1) * itemsPerPage, itemsPerPage);

        List<WiseSaying> content = sql.selectRows(WiseSaying.class);

        return Pageable.<WiseSaying>builder()
                .totalItems(totalItems)
                .itemsPerPage(itemsPerPage)
                .page(page)
                .content(content)
                .build();
    }

    @Override
    public int count(String keywordType, String keyword) {
        Sql sql = simpleDb.genSql();

        sql.append("SELECT COUNT(*) FROM wiseSaying");

        switch (keywordType) {
            case "content":
                sql.append("WHERE content LIKE ?", "%" + keyword + "%");
                break;
            case "author":
                sql.append("WHERE author LIKE ?", "%" + keyword + "%");
                break;
        }

        return (int) sql.selectLong();
    }

    @Override
    public Pageable<WiseSaying> pageable(String keywordType, String keyword, int itemsPerPage, int page) {
        int totalItems = count(keywordType, keyword);

        Sql sql = simpleDb.genSql();

        sql.append("SELECT * FROM wiseSaying");

        switch (keywordType) {
            case "content":
                sql.append("WHERE content LIKE ?", "%" + keyword + "%");
                break;
            case "author":
                sql.append("WHERE author LIKE ?", "%" + keyword + "%");
                break;
        }

        sql.append("ORDER BY id DESC")
                .append("LIMIT ?, ?", (page - 1) * itemsPerPage, itemsPerPage);

        List<WiseSaying> content = sql.selectRows(WiseSaying.class);

        return Pageable.<WiseSaying>builder()
                .totalItems(totalItems)
                .itemsPerPage(itemsPerPage)
                .page(page)
                .keywordType(keywordType)
                .keyword(keyword)
                .content(content)
                .build();
    }
}

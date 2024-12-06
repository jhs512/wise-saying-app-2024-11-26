package com.ll.standard.simpleDb;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sql {
    private final SimpleDb simpleDb;
    private final StringBuilder sqlFormat;
    private final List<Object> params;

    public Sql(SimpleDb simpleDb) {
        this.simpleDb = simpleDb;
        this.sqlFormat = new StringBuilder();
        this.params = new ArrayList<>();
    }

    public Sql append(String sqlBit, Object... params) {
        this.sqlFormat.append("\n" + sqlBit);

        for (Object param : params) {
            this.params.add(param);
        }

        return this;
    }

    public Sql appendIn(String sqlBit, Object... params) {
        String inClause = IntStream.range(0, params.length)
                .mapToObj(i -> "?")
                .collect(Collectors.joining(", "));

        sqlBit = sqlBit.replace("?", inClause);

        return append(sqlBit, params);
    }

    private String toSql() {
        return sqlFormat.toString();
    }

    public long insert() {
        return simpleDb.insert(toSql(), params.toArray());
    }

    public int update() {
        return simpleDb.update(toSql(), params.toArray());
    }

    public int delete() {
        return simpleDb.delete(toSql(), params.toArray());
    }

    public List<Map<String, Object>> selectRows() {
        return simpleDb.selectRows(toSql(), params.toArray());
    }

    public Map<String, Object> selectRow() {
        return simpleDb.selectRow(toSql(), params.toArray());
    }

    public LocalDateTime selectDatetime() {
        return simpleDb.selectDatetime(toSql(), params.toArray());
    }

    public long selectLong() {
        return simpleDb.selectLong(toSql(), params.toArray());
    }

    public String selectString() {
        return simpleDb.selectString(toSql(), params.toArray());
    }

    public boolean selectBoolean() {
        return simpleDb.selectBoolean(toSql(), params.toArray());
    }

    public List<Long> selectLongs() {
        return simpleDb.selectLongs(toSql(), params.toArray());
    }

    public <T> List<T> selectRows(Class<?> cls) {
        return simpleDb
                .selectRows(toSql(), cls, params.toArray());
    }

    public <T> T selectRow(Class<?> cls) {
        return simpleDb.selectRow(toSql(), cls, params.toArray());
    }
}
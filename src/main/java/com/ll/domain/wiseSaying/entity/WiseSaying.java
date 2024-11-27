package com.ll.domain.wiseSaying.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public WiseSaying(Map<String, Object> map) {
        this.id = (int) map.get("id");
        this.content = (String) map.get("content");
        this.author = (String) map.get("author");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        WiseSaying wiseSaying = (WiseSaying) other;

        if (id != wiseSaying.id) {
            return false;
        }

        if (!content.equals(wiseSaying.content)) {
            return false;
        }

        return author.equals(wiseSaying.author);
    }

    public boolean isNew() {
        return id == 0;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", id);
        map.put("content", content);
        map.put("author", author);

        return map;
    }
}

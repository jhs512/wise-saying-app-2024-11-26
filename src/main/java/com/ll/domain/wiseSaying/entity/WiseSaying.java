package com.ll.domain.wiseSaying.entity;

import lombok.Getter;
import lombok.Setter;

public class WiseSaying {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public boolean isNew() {
        return id == 0;
    }
}

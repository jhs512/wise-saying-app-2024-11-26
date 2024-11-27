package com.ll.domain.wiseSaying.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WiseSaying {
    private int id;
    private String content;
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

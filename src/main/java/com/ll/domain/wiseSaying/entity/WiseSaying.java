package com.ll.domain.wiseSaying.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public boolean isNew() {
        return id == 0;
    }
}

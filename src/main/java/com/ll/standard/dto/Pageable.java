package com.ll.standard.dto;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Pageable {
    private int totalItems;
    private List<WiseSaying> content;
}

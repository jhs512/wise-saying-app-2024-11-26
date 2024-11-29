package com.ll.standard.dto;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Pageable {
    private int totalItems;
    private List<WiseSaying> content;
}

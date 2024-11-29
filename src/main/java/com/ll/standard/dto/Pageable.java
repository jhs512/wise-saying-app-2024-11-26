package com.ll.standard.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Pageable<T> {
    private int totalItems;
    private int totalPages;
    private int itemsPerPage;
    private int page;
    private List<T> content;
}

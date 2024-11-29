package com.ll.standard.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Pageable<T> {
    private int totalItems;
    private int totalPages;
    private int itemsPerPage;
    private int page;
    private String keywordType;
    private String keyword;
    private List<T> content;

    @Builder
    public Pageable(int totalItems, int itemsPerPage, int page, String keywordType, String keyword, List<T> content) {
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;
        this.page = page;
        this.keywordType = keywordType;
        this.keyword = keyword;
        this.content = content;
        this.totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
    }
}

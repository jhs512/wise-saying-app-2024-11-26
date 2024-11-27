package com.ll.domain.wiseSaying.controller;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
        this.wiseSayingService = new WiseSayingService();
    }

    public void actionAdd() {
        System.out.println("명언 : ");
        String content = scanner.nextLine();
        System.out.println("작가 : ");
        String author = scanner.nextLine();

        WiseSaying wiseSaying = wiseSayingService.add(content, author);

        System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> wiseSayings = wiseSayingService.findAll();

        for (WiseSaying wiseSaying : wiseSayings.reversed()) {
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
        }
    }
}

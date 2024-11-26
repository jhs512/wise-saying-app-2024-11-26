package com.ll.domain.wiseSaying.controller;

import com.ll.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;
    private List<WiseSaying> wiseSayings;
    private int lastId;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
        this.wiseSayings = new ArrayList<>();
        this.lastId = 0;
    }

    public void actionAdd() {
        System.out.println("명언 : ");
        String content = scanner.nextLine();
        System.out.println("작가 : ");
        String author = scanner.nextLine();

        int id = ++lastId;

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (WiseSaying wiseSaying : wiseSayings.reversed()) {
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
        }
    }
}

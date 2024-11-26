package com.ll.domain.wiseSaying.controller;

import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void actionAdd() {
        System.out.println("명언 : ");
        System.out.println("작가 : ");
    }
}

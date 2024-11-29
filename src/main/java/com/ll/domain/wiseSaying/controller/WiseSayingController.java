package com.ll.domain.wiseSaying.controller;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.service.WiseSayingService;
import com.ll.global.app.Command;
import com.ll.standard.dto.Pageable;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WiseSayingController {
    private final Scanner scanner;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
        this.wiseSayingService = new WiseSayingService();
    }

    public void actionAdd() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        WiseSaying wiseSaying = wiseSayingService.add(content, author);

        System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
    }

    public void actionList(Command command) {
        String keyword = command.getParam("keyword", "");
        String keywordType = command.getParam("keywordType", "content");
        int itemsPerPage = 5;
        int page = command.getParamAsInt("page", 1);

        boolean hasKeyword = !keyword.isBlank();

        Pageable<WiseSaying> pageable = hasKeyword
                ? wiseSayingService.pageable(keywordType, keyword, itemsPerPage, page)
                : wiseSayingService.pageableAll(itemsPerPage, page);

        if (hasKeyword) {
            System.out.println("----------------------");
            System.out.println("검색타입 : " + keywordType);
            System.out.println("검색어 : " + keyword);
            System.out.println("----------------------");
        }

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (WiseSaying wiseSaying : pageable.getContent()) {
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
        }

        System.out.println("----------------------");

        System.out.print("페이지 : ");
        String pageMenu = IntStream.rangeClosed(1, pageable.getTotalPages())
                .mapToObj(i -> i == page ? "[" + i + "]" : String.valueOf(i))
                .collect(Collectors.joining(" "))
                .toString();
        System.out.println(pageMenu);
    }

    public void actionDelete(Command command) {
        int id = command.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id(숫자)를 입력해주세요.");
            return;
        }

        boolean removed = wiseSayingService.deleteById(id);

        if (!removed) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    public void actionModify(Command command) {
        int id = command.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id(숫자)를 입력해주세요.");
            return;
        }

        Optional<WiseSaying> opWiseSaying = wiseSayingService.findById(id);

        if (opWiseSaying.isEmpty()) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying wiseSaying = opWiseSaying.get();
        System.out.println("명언(기존) : " + wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.println("작가(기존) : " + wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        wiseSayingService.modify(wiseSaying, content, author);
    }

    public void actionBuild() {
        wiseSayingService.build();

        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }

    public void makeSampleData(int items) {
        wiseSayingService.makeSampleData(items);
    }
}

package com.ll;

import com.ll.domain.system.controller.SystemController.SystemController;
import com.ll.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private final SystemController systemController;
    private final WiseSayingController wiseSyingController;

    public App(Scanner scanner) {
        this.scanner = scanner;
        this.systemController = new SystemController();
        this.wiseSyingController = new WiseSayingController(scanner);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if ("종료".equals(cmd)) {
                systemController.actionExit();
                break;
            } else if ("등록".equals(cmd)) {
                wiseSyingController.actionAdd();
            } else if ("목록".equals(cmd)) {
                wiseSyingController.actionList();
            }
        }
    }
}

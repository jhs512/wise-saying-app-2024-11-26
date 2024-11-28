package com.ll.global.app;

import com.ll.domain.system.controller.SystemController;
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

            Command command = new Command(cmd);

            switch (command.getActionName()) {
                case "종료":
                    systemController.actionExit();
                    return;
                case "등록":
                    wiseSyingController.actionAdd();
                    break;
                case "목록":
                    wiseSyingController.actionList(command);
                    break;
                case "삭제":
                    wiseSyingController.actionDelete(command);
                    break;
                case "수정":
                    wiseSyingController.actionModify(command);
                    break;
                case "빌드":
                    wiseSyingController.actionBuild();
                    break;
            }
        }
    }

    public void makeSampleData(int items) {
        wiseSyingController.makeSampleData(items);
    }
}

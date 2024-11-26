package com.ll;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // lab1();
        // lab2();

        lab3();
    }

    private static void lab1() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("명령) ");
        String cmd = scanner.nextLine().trim();

        System.out.println("입력한 명령: " + cmd);
    }

    private static void lab2() {
        InputStream in = new ByteArrayInputStream("안녕\n잘가".getBytes());
        Scanner scanner = new Scanner(in);

        System.out.println("명령) ");
        String cmd = scanner.nextLine().trim();

        System.out.println("입력한 명령: " + cmd);

        System.out.println("명령) ");
        cmd = scanner.nextLine().trim();

        System.out.println("입력한 명령: " + cmd);
    }

    private static void lab3() {
        System.out.println("== 시작 ==");

        System.out.println("안녕하세요.");
        System.out.println("반갑습니다.");

        System.out.println("== 끝 ==");
    }
}
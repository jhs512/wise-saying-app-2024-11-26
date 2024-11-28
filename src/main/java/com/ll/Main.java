package com.ll;

import com.ll.global.app.App;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        new App(scanner).run();

        scanner.close();
    }
}
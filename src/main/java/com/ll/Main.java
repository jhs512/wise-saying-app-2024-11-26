package com.ll;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        new App(scanner).run();

        scanner.close();
    }
}
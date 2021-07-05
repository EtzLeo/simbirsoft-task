package ru.leo.simbirsofttask;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        String content = ContentExtraction.getHtmlContent(url);
        System.out.println(content);
    }
}

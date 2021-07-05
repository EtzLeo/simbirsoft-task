package ru.leo.simbirsofttask;

import ru.leo.simbirsofttask.util.ContentExtraction;
import ru.leo.simbirsofttask.util.HtmlConverter;

import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        String content = ContentExtraction.getHtmlContent(url);

        Map<String, Integer> statistic = HtmlConverter.getWordsStatistic(content);
        for (String key: statistic.keySet()) {
            System.out.println(key + " - " + statistic.get(key));
        }
    }
}

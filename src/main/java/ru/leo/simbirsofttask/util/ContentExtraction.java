package ru.leo.simbirsofttask.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Извлечение контента.
 */
public class ContentExtraction {

    /**
     * Получение контента html-страницы.
     *
     * @param address адрес страницы
     * @return html-контент в виде строки
     */
    public  static String getHtmlContent(String address) {
        try(
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        new URL(address).openConnection().getInputStream()
                                )
                        );
                ) {
            String content = "";
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    return content;
                }
                content += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Ошибка при получении контента html-страницы: " + e.getMessage());
        }
        return "";
    }
}

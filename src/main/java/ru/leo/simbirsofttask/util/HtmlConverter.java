package ru.leo.simbirsofttask.util;

import org.jsoup.Jsoup;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Преобразователь html.
 */
public class HtmlConverter {

    /**
     * Получение списка слов из строки, содержащей html.
     *
     * @param html строка с html
     * @return список слов
     */
    public static List<String> getWords(String html) {
        String text = Jsoup.parse(html).body().text();
        return Arrays.asList(text.split("[\\s.,!?\"«';:\\[\\]()]+"));
    }

    /**
     * Получение статистики количества уникальных слов.
     *
     * @param html строка с html
     * @return статистика в виде словаря, где ключ - это слово, а значение - то, сколько раз оно встречается
     */
    public static Map<String, Integer> getWordsStatistic(String html) {
        List<String> words = getWords(html);
        Map<String, Integer> wordsStatistic = new TreeMap<>();

        for (String word: words) {
            if (wordsStatistic.containsKey(word.toUpperCase())) {
                wordsStatistic.put(word.toUpperCase(), wordsStatistic.get(word.toUpperCase()) + 1);
            }
            else {
                wordsStatistic.put(word.toUpperCase(), 1);
            }
        }
        return wordsStatistic;
    }
}

package ru.leo.simbirsofttask.util;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.leo.simbirsofttask.model.WordFrequency;
import ru.leo.simbirsofttask.util.HtmlConverter;

import java.util.*;

public class HtmlConverterTest {

    String html;
    @BeforeEach
    public void init(){
        html =
                "<!DOCTYPE html><html><head><meta charset = \"utf-8\" / >" +
                        "\n<title> HTML5 </title><style>" +
                        "article, aside, details, figcaption, figure, footer, " +
                        "header, hgroup, menu, nav, section { display: block; } " +
                        "</style></head><body><p> Hello hello, world! </p></body></html>";
    }
    @Test
    @Description("Тест преобразования html-строки в список слов.")
    public void testGetWords() {
        List<String> words = Arrays.asList("Hello", "hello", "world");
        Assertions.assertEquals(words, HtmlConverter.getWords(html));
    }

    @Test
    @Description("Тест преобразования html-строки в словарь(ключ - слово, значение - сколько раз встречается).")
    public void testGetStatistic() {
        Map<String, Integer> statistic = new HashMap<>();
        statistic.put("HELLO", 2);
        statistic.put("WORLD", 1);
        Assertions.assertEquals(statistic, HtmlConverter.getWordsStatistic(html));
    }

    @Test
    @Description("Тест преобразования словаря в класс WordFrequency")
    public void testToWordFrequency() {
        List<WordFrequency> wordFrequencies = Arrays.asList(
                new WordFrequency("HELLO", 2),
                new WordFrequency("WORLD", 1)
        );
        Assertions.assertEquals(wordFrequencies, HtmlConverter.toWordFrequency(html));
    }
}

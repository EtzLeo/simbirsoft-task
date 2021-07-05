package ru.leo.simbirsofttask;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.leo.simbirsofttask.util.ContentExtraction;

public class ContentExtractionTest {

    @Test
    @Description("Проверка извлечения текста из html-страницы")
    public void testGetContent() {
        Assertions.assertEquals("", ContentExtraction.getHtmlContent("https://www.s.com/"));
        Assertions.assertEquals("", ContentExtraction.getHtmlContent("Просто строка"));
    }
}

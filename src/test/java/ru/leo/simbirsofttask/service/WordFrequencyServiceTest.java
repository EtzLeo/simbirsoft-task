package ru.leo.simbirsofttask.service;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.leo.simbirsofttask.dsprovider.DataSourceProvider;
import ru.leo.simbirsofttask.model.WordFrequency;
import ru.leo.simbirsofttask.repository.WordFrequencyRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordFrequencyServiceTest {

    DataSourceProvider dataSourceProvider;
    WordFrequencyRepository wordFrequencyRepository;
    WordFrequencyService service;
    WordFrequency hello;
    WordFrequency world;

    @BeforeEach
    void init() throws IOException {
        dataSourceProvider = new DataSourceProvider();

        try(Connection connection = dataSourceProvider.getDataSource().getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE test_stat");
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }

        wordFrequencyRepository = new WordFrequencyRepository(dataSourceProvider.getDataSource(), "test_stat");
        service = new WordFrequencyService(wordFrequencyRepository);

        hello = new WordFrequency( "HELLO", 2);
        service.insert(hello);

        world = new WordFrequency( "WORLD", 1);
        service.insert(world);
    }

    @Test
    @Description("Тест возвращения всех записей БД и создания записи.")
    public void testGetAll() throws IOException {
        List<WordFrequency> wordFrequencyList = new ArrayList<>(Arrays.asList(hello, world));
        Assertions.assertEquals(wordFrequencyList, service.getAll());

        WordFrequency and = new WordFrequency("AND", 1);

        service.insert(and);
        wordFrequencyList.add(2,and);
        Assertions.assertEquals(wordFrequencyList, service.getAll());
    }
}

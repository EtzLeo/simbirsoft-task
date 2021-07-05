package ru.leo.simbirsofttask.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.leo.simbirsofttask.model.WordFrequency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для доступа к таблице с данными о статистике слов.
 */
public class WordFrequencyRepository {

    /**
     * Название таблицы.
     */
    private final String TABLE_NAME;

    /**
     * Источник данных.
     */
    private EmbeddedDataSource dataSource;

    public WordFrequencyRepository(EmbeddedDataSource dataSource, String tableName) {
        this.dataSource = dataSource;
        this.TABLE_NAME = tableName;
        initTable();
    }

    /**
     * Инициализация БД.
     */
    private void initTable() {
        System.out.printf("Инициализация таблицы %s%n", TABLE_NAME);
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Таблица уже существует");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "id INTEGER PRIMARY KEY NOT NULL " +
                                "GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT BY 1),"
                                + "word VARCHAR(255),"
                                + "frequency INTEGER"
                                + ")");
                System.out.println("Таблица успешно инициализирована");
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при инициализации таблицы: " + e.getMessage());
        } finally {
            System.out.println("=========================");
        }
    }

    /**
     * Метод поиска всех слов в БД.
     *
     * @return список всех созданных слов
     */
    public List<WordFrequency> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<WordFrequency> words = new ArrayList<>();
            while (resultSet.next()) {
                words.add(
                        new WordFrequency(
                                resultSet.getInt("id"),
                                resultSet.getString("word"),
                                resultSet.getInt("frequency")));
            }
            return words;
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Создание записи в БД о новом слове.
     *
     * @param wordFrequency человек
     */
    public void createNew(WordFrequency wordFrequency) {
        String sqlQuery =
                "INSERT INTO "
                        + TABLE_NAME
                        + "(word, frequency)"
                        + " VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, wordFrequency.getWord());
            statement.setInt(2, wordFrequency.getFrequency());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Изменение данных в БД о слове.
     *
     * @param wordFrequency слово
     */
    public void update(WordFrequency wordFrequency) {
    }

    /**
     * Удаление из БД записи о слове.
     *
     * @param id идентификатор
     */
    public void delete(int id) {
    }
}

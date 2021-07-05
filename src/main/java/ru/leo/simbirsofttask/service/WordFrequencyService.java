package ru.leo.simbirsofttask.service;

import ru.leo.simbirsofttask.model.WordFrequency;
import ru.leo.simbirsofttask.repository.WordFrequencyRepository;

import java.util.List;

/**
 * Сервис для взаимодействия с репозиторием.
 */
public class WordFrequencyService {

    /**
     * Репозиторий для доступа к таблице с данными.
     */
    private final WordFrequencyRepository repository;

    public WordFrequencyService(WordFrequencyRepository repository) {
        this.repository = repository;
    }

    /**
     * Вставляет новую строку данных о слове в таблицу.
     *
     * @param wordFrequency слово
     */
    public void insert(WordFrequency wordFrequency) {
        repository.createNew(wordFrequency);
    }

    /**
     * Возвращает все записи о словах из таблицы.
     *
     * @return список слов
     */
    public List<WordFrequency> getAll() {
        return repository.findAll();
    }

}

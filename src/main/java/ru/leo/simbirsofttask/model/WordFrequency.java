package ru.leo.simbirsofttask.model;

import java.util.Objects;

/**
 * Частота встречаемости слова.
 */
public class WordFrequency {

    /**
     * Идентификатор.
     */
    private int id;

    /**
     * Слово.
     */
    private String word;

    /**
     * Частота встречаемости.
     */
    private int frequency;

    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public WordFrequency(int id, String word, int frequency) {
        this(word, frequency);
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordFrequency that = (WordFrequency) o;
        return frequency == that.frequency && Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, frequency);
    }

    @Override
    public String toString() {
        return word + " - " + frequency;
    }

}

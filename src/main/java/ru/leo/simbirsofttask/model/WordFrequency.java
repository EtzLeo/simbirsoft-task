package ru.leo.simbirsofttask.model;

/**
 * Частота встречаемости слова.
 */
public class WordFrequency {

    /**
     * Слово.
     */
    private String word;

    /**
     * Частота встречаемости.
     */
    private String frequency;

    public WordFrequency(String word, String frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "WordFrequency{" +
                "word='" + word + '\'' +
                ", frequency='" + frequency + '\'' +
                '}';
    }
}

package model;

import java.util.List;

/**
 * Модель для обработки ответов
 */
public class YandexResponse implements Response {
    public YandexResponse(List<Word> words) {
        this.words = words;
    }

    private final List<Word> words;

    public List<Word> getWords() {
        return words;
    }
}

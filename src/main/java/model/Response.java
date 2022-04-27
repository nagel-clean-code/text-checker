package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Интерфейс для моделей оброботки ответов от speller сервисов
 */
public interface Response {
    /**
     * @see Word класс для десериализации объектов из JSON - (слова с ошибками)
     */
     class Word {
        public int code;//Код ошибки
        public int pos; //Позиция слова с ошибкой
        public int row; //Номер строки (отсчёт с 0)
        public int col; //Номер столбца (отсчёт с 0)
        public int len; //Длинна слова с ошибкой
        public String word;         //исходное слово
        public ArrayList<String> s; //Возможные вариации исправления
    }

    /**
     * @return Метод возвращающий список десериализованных объектов - ошибочных слов
     */
    List<Word> getWords();
}

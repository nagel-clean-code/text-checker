package api.yandex;

import api.yandex.entity.CodeErrors;
import model.Response;
import model.YandexResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class YandexSpellerClientTest {
    private final YandexSpellerClient spellerClient = new YandexSpellerClient();

    @Test
    @DisplayName("Тест без ошибок")
    void correctText() throws Exception {
        String text = "when the bin is created (and in accordance with " +
                "the standard lifecycle callbackcontract described previously). " +
                "This feature also enforces a consistent naming convention for " +
                "initialization and destroy method callbacks";
        YandexResponse resp = spellerClient.setText(text);
        Assertions.assertEquals(resp.getWords().size(), 0);
    }

    @Test
    @DisplayName("Тест с одной ошибкой")
    void oneWrongWord() throws Exception {
        String text = "when the bean is created (and in accordance with " +
                "the standard lifecycle callbackcontract described previously). " +
                "This feature also enforces a consistent naming convention for " +
                "initialization and destroy method callbacks";
        YandexResponse resp = spellerClient.setText(text);
        Assertions.assertEquals(resp.getWords().size(), 1);
    }

    @Test
    @DisplayName("Тест с полностью неверным текстом")
    void fullWrongText() throws Exception {
        String text = "whhen thhe beean iis ccreated (aand inn aaccordance wwith " +
                "tthe sstandard llifecycle ccallbackcontract ddescribed ppreviously). " +
                "TThis ffeature aalso eenforces ai cconsistent nnaming cconvention ffor " +
                "iinitialization annd ddestroy mmethod ccallbacks";
        YandexResponse resp = spellerClient.setText(text);
        Assertions.assertEquals(resp.getWords().size() , text.split(" ").length - 1);

        // Пояснение к тесту: (тест с погрешностью в одно слово)
        // Яндекс спейллер известны не все слова (например абривиатуры, названия фулканов),
        // в ответ он отправляет только те, на которые он может предложить замену.
        // В данном тесте одно слово было не распознано.
    }


    //Output all errors to the console (Для дебага)
    public void printMisspelledWords(List<Response.Word> words) {
        if (words.size() > 0) {
            words.forEach(
                    w -> {
                        System.out.print("Код ошибки " + w.code + ", \"" + w.word + "\" ");
                        System.out.println(CodeErrors.getError(w.code));
                        System.out.print("Позиция: " + w.pos);
                        System.out.println(", строка: " + w.row + ", столбец: " + w.col + ", длина: " + w.len);
                        System.out.print((w.s.size() > 0) ? "Возможно Вы имели ввиду: " : "");
                        w.s.forEach(
                                st -> System.out.print(st + " ")
                        );
                        System.out.println("\n");
                    }
            );
        } else {
            System.out.println("Ошибок не найдено");
        }
    }
}
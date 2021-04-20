package api.yandex.entity;

public class CodeErrors {
    public static String getError(int code) {
        switch (code) {
            case 1:
                return "слова нет в словаре";
            case 2:
                return "повтор слова";
            case 3:
                return "неверное употребление прописных и строчных букв";
            case 4:
                return "текст содержит слишком много ошибок";
            default:
                return "Код ошибки отсутствует";
        }
    }
}
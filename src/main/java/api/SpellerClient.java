package api;

import model.Response;

/**
 * Интерфейс для Speller сервисов
 * @param <T> - модель соответствующая сервису
 */
public interface SpellerClient<T extends Response> {

    /**
     * @param text - Текст, посылаемый на проверку.
     * используется http get запрос к серверу.
     * @return возвращает модель - результат обработанного текста
     *
     * @see api.yandex.YandexSpellerClient:
     * максимальный размер передаваемой строки 10Кб.
     * В данном методе настройки спейллера не используются
     * @throws Exception - "Request error" - ошибка во время формирования или исполнения запроса
     */
    T setText(String text) throws Exception;

    /**
     * @param text - Текст, посылаемый на проверку.
     * используется http get запрос к серверу.
     * @return возвращает модель - результат обработанного текста, соответствующая используемому спейллору
     * @throws Exception - "Request error" - ошибка во время формирования или исполнения запроса
     *
     * @see api.yandex.YandexSpellerClient:
     * максимальный размер передаваемой строки 10Кб.
     * @param settings - настройка спейллера формирующаяся из суммы констант-опций файла:
     * @see api.yandex.entity.SettingsSpeller
     */
    T setText(String text, int settings) throws Exception;

}

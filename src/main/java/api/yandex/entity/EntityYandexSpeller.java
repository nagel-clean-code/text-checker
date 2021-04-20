package api.yandex.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.http.HttpClient;

/**
 * Сущность YandexSpeller
 */
public class EntityYandexSpeller {
    public static Logger getLogger() {
        return logger;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public int getSettings() {
        return settings;
    }

    public void setSettings(int settings) {
        this.settings = settings;
    }

    private static final Logger logger = LogManager.getLogger();
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    private int settings = 0;
}

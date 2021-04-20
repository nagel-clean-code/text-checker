package api.yandex;

import api.yandex.entity.EntityYandexSpeller;
import model.YandexResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import api.SpellerClient;
import model.Response;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

/**
 * Клиент для отправки запросов на сервер
 * @return возвращает модель для работы с ответом:
 * @see YandexResponse
 */
public class YandexSpellerClient implements SpellerClient<YandexResponse> {
    private EntityYandexSpeller data = new EntityYandexSpeller();

    public YandexSpellerClient(){ }

    @Override
    public YandexResponse setText(String text) throws Exception {

        try{
            return getRequestCheck(text);
        }catch (Exception ex){
            EntityYandexSpeller.getLogger().error("Error message", ex);
            throw new Exception("Request error");
        }
    }

    @Override
    public YandexResponse setText(String text, int settings) throws Exception{
        data.setSettings(settings);
        try{
            return getRequestCheck(text);
        }catch (Exception ex){
            EntityYandexSpeller.getLogger().error("Error message", ex);
            throw new Exception("Request error");
        }
    }

    private String getUrlAPI() throws IOException {
        Properties props = new Properties();

        props.load(new BufferedInputStream((
                Objects.requireNonNull(
                        ClassLoader.getSystemResourceAsStream("config.properties"))
        )));
        return props.getProperty("linkAPI");
    }

    //Sending text for analysis
    private YandexResponse getRequestCheck(String text) throws Exception {
        String generateURL = getUrlAPI() +
                "?text=" + URLEncoder.encode(text, "UTF-8") +
                ((data.getSettings() > 0) ? ("&options=" + data.getSettings()) : "");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(generateURL))
                .build();

        HttpResponse<String> httpResp = data.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return deserialization(httpResp);
    }

    private YandexResponse deserialization(HttpResponse<String> httpResp){
        Type typeClass = new TypeToken<ArrayList<Response.Word>>() { }.getType();
        ArrayList<Response.Word> respList = new Gson().fromJson(httpResp.body(), typeClass);
        return new YandexResponse(respList);
    }
}

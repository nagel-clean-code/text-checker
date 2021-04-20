package api.yandex.entity;

public enum SettingsSpeller {
    IGNORE_DIGITS(2),            //Пропускать слова с цифрами
    IGNORE_URLS(4),              //Пропускать интернет-адреса, почтовые адреса и имена файлов.
    FIND_REPEAT_WORDS(8),        //Пропускать повторяющиеся слова
    IGNORE_CAPITALIZATION(512);  //игнорировать прописные/строчные буквы

    SettingsSpeller(int code) {
        this.code = code;
    }

    public int code;
}

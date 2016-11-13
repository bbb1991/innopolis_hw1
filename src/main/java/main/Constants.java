package main;

/**
 * Created by bbb1991 on 11/14/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class Constants {

    /**
     * Токен для разбивки текста на слова.
     */
    public static final String TOKEN = "[\\s]+";

    /**
     * Паттерн, который проверяет, есть ли в тексте неразрешенные символы
     */
    public static final String FOREIGN_SYMBOL_PATTERN = "[[^?!:;.,…@&()\\[\\]{\\}\\-+`\'\"]&&[^@',&]&&[^А-Яа-я0-9_\\s]]";

    /**
     * Паттерн, проверяющий, что поданный на вход можно ли интерпретировать как обьект URL или нет
     * URL обьектами являются обьекты, у которых протокол начинается с http(-s)://, (s-)ftp://, file://
     */
    public static final String VALID_URL_PATTERN =
            "^(?:(ht|f)tp(s?)\\:\\/\\/)?[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&amp;%\\$#_]*)?$";
}

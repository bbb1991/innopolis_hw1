package readers;

import helper.StringHelper;

import java.util.regex.Pattern;

import static main.Constants.*;

/**
 * Created by bbb1991 on 11/3/16.
 * "Фабрика" возвращающая ридер по типу ресурсов
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class ReaderFactory {

    private ReaderFactory() {}

    public static AbstractResourceReader getResourceReader(String resource) {

        StringHelper helper = new StringHelper();
        Pattern pattern = Pattern.compile(VALID_URL_PATTERN);

        if (pattern.matcher(resource).find()) { // // TODO: 11/4/16 improve checking resource type
            return new URLBasedResourceReader(helper); // если есть протокол перед именем ресурсов типа http(s), (s)ftp, file
        } else {
            return new FileBasedResourceReader(helper); // иначе все считаем файлом
        }
    }
}

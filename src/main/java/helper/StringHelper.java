package helper;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by bbb1991 on 11/2/16.
 * Класс для работы со считанной строкой.
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class StringHelper {

    /**
     * Токен для разбивки текста на слова.
     */
    private static final String TOKEN = "[\\s]+";

    private static final Logger logger = Logger.getLogger(StringHelper.class);

    /**
     *  Метод, который разбивает текст по какому либо токену. Было решено брать
     *  в качестве токена пробелы
     * @param text текст который нужно разбить
     * @return массив слов, разбитый по токену
     */
    public String[] split(final String text)  {
        logger.debug("Incoming text for split: " + text);
        return text.split(TOKEN);
    }

    /**
     * Метод, который проверяет, содержит ли переданный текст символы ин языков.
     *
     * То есть по условию задачи текст должен содержать только кириллицу, знаки пунктуаций и цифры.
     * Насчет кириллицы и цифр все понятно. А вот насчет знаков пунктуаций на заданий не было уточнений
     * так что было решено брать список из википедий
     * @see <a href="https://ru.wikipedia.org/wiki/%D0%97%D0%BD%D0%B0%D0%BA%D0%B8_%D0%BF%D1%80%D0%B5%D0%BF%D0%B8%D0%BD%D0%B0%D0%BD%D0%B8%D1%8F">Wiki</a></a>
     *
     * Так же было неясно насчет нижнего подчеркивания, так как он входит в группу буквенно-цифр (\w)
     * но в условий задания о нем не было слова но все же было решено интерпретировать его как корректный символ
     *
     * @param text текст, который надо проверить
     * @return результат проверки булевое значение
     */
    public boolean isTextContainForeignSymbol(final String text) {
        logger.debug("Checking text if contain foreign symbols.");
        logger.debug("Text: " + text);

        Pattern pattern = Pattern.compile("[[^?!:;.,…@&()\\[\\]{\\}\\-+`\'\"]&&[^@',&]&&[^А-Яа-я0-9_\\s]]");
        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }


    /**
     * Метод, который проверяет, в считанной строке есть ли дубликаты
     * @param words считанная строка, разбитая по пробелу
     * @param set уже существующая сет (сет со словами, которые были считаны ранее в этом же ресурсе
     * @return <code>true</code> - если содержится дубликаты и <code>false</code> если не содержит
     */
    public boolean isTextContainDuplicates(String[] words, Set<String> set) {
        logger.debug("Checking if text contain duplicates");
        logger.debug("Array is: " + Arrays.deepToString(words));

        int sizeBefore = set.size(); // определяем, сколько слова уже содержится в сете

        set.addAll(Arrays.stream(words).collect(Collectors.toList())); // добавляем новые слова

        return sizeBefore + words.length != set.size(); // проверяем все ли слова добавились
    }
}

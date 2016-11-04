package helper;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by bbb1991 on 11/2/16.
 *
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
     * @throws IllegalArgumentException если была передана пустая строка
     */
    public String[] split(final String text) throws IllegalArgumentException{
//        logger.info("Splitting line");

//        if (text.trim().isEmpty()) {
//            throw new IllegalArgumentException("Passed string is empty!");
//        }
        return text.split(TOKEN);
    }

    /**
     * Метод, который проверяет, содержит ли переданный текст символы ин языков.
     *
     * То есть по условию задачи текст должен содержать только кириллицу, знаки пунктуаций и цифры.
     * Насчет кириллицы и цифр все понятно. А вот насчет знаков пунктуаций было решено брать список
     * из википедий @see https://ru.wikipedia.org/wiki/%D0%97%D0%BD%D0%B0%D0%BA%D0%B8_%D0%BF%D1%80%D0%B5%D0%BF%D0%B8%D0%BD%D0%B0%D0%BD%D0%B8%D1%8F
     *
     * Так же было неясно насчет нижнего подчеркивания, так как он входит в группу буквенно-цифр (\w)
     * но в условий задания о нем не было слова но все же было решено интерпретировать его как корректный сивол
     *
     * @param text текст, который надо проверить
     * @return результат проверки булевое значение
     */
    public boolean isTextContainForeignSymbol(final String text) {
//        logger.info("Checking text if contain foreign symbols");

        Pattern pattern = Pattern.compile("[\\\\p{Punct}&&[^@',&]]&&[^А-Яа-я0-9_\\s]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        return matcher.find();
    }

    @Deprecated
    public boolean isTextContainDuplicates(String[] words) {
//        logger.info("");
        Set<String> set = new HashSet<>(Arrays.asList(words));
        return set.size() != words.length;
    }


    public boolean isTextContainDuplicates(String[] words, Set<String> set) {
//        logger.info("Checking if text contain duplicates");
        int sizeBefore = set.size();
        set.addAll(Arrays.stream(words).collect(Collectors.toList()));
        return sizeBefore + words.length != set.size();

    }
}

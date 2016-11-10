package readers;

import helper.StringHelper;
import main.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static main.States.*;

/**
 * Created by bbb1991 on 11/3/16.
 * Абстрактный ридер, от которого наследуется все ридеры
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public abstract class AbstractResourceReader {

    /**
     * Класс хелпер, в котором реализованы все методы, необходимые для работы с ресурсом
     */
    private final StringHelper helper;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    AbstractResourceReader(StringHelper helper) {
        this.helper = helper;
    }

    /**
     * Метод в для создания необходимого ридера.
     * @param file ресурс, с котором необходимо работать
     * @return сет со словами из ресурса
     */
    public abstract Set<String> read(final String file);

    /**
     * Метод в котором происходит вся логика. Был вынесен отдельно, так как работа FileBasedResourceReader и
     * URLBasedResourceReader совпадал
     * @param bufferedReader ридер на основе ресурса
     * @return слова из файла разбитые по пробелам
     */
    public Set<String> process(BufferedReader bufferedReader) {

        Set<String> set = new HashSet<>(); // все слова, которые встречаются в файле

        String line;

        try {
            // читаем пока не кончилась строки или пока другой поток не отрапортовал что можно отменять
            while ((line = bufferedReader.readLine()) != null && States.getFlag() == States.OK) {

                // проверяем, не содержит ли считанный текст
                if (helper.isTextContainForeignSymbol(line)) {
                    States.setFlag(FOREIGN_SYMBOL_FOUND);
                    logger.error("Thread {}: Foreign symbol found!", Thread.currentThread().getName());
                }

                String[] words = helper.split(line);

                // проверяем, не встречался ли слова в данной строке ранее.
                if (helper.isTextContainDuplicates(words, set)) {
                    States.setFlag(DUPLICATE_FOUND);
                    logger.error("Thread {}: text contain duplicates!", Thread.currentThread().getName());
                }
            }
        } catch (IOException e) {
            States.setFlag(UNEXPECTED_ERROR);
        }

        if (States.getFlag() == States.OK) {
            logger.info("Reading completed!");
        }

        return set;
    }
}

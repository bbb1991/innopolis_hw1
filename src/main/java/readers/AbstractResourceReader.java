package readers;

import helper.StringHelper;
import jdk.nashorn.internal.ir.BlockStatement;
import main.Box;
import main.States;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static main.States.DUPLICATE_FOUND;
import static main.States.FOREIGN_SYMBOL_FOUND;
import static main.States.UNEXPECTED_ERROR;

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

    final Logger logger = Logger.getLogger(this.getClass());

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
                    logger.error(String.format("Thread %s: Foreign symbol found!", Thread.currentThread().getName()));
                }

                String[] words = helper.split(line);

                // проверяем, не встречался ли слова в данной строке ранее.
                if (helper.isTextContainDuplicates(words, set)) {
                    States.setFlag(DUPLICATE_FOUND);
                    logger.error(String.format("Thread %s: text contain duplicates!", Thread.currentThread().getName()));
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

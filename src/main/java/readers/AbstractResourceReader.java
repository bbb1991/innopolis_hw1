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
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public abstract class AbstractResourceReader {

    protected final StringHelper helper;

    protected final Logger logger = Logger.getLogger(this.getClass());

    protected AbstractResourceReader(StringHelper helper) {
        this.helper = helper;
    }

    public abstract Set<String> read(final String file);

    public Set<String> process(BufferedReader bufferedReader) {
        Set<String> set = new HashSet<>();

        String line;

        try {
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

package readers;

import helper.StringHelper;
import main.Box;
import main.Constants;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

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
            while ((line = bufferedReader.readLine()) != null && Box.flag == Constants.SUCCESS) {

                // проверяем, не содержит ли считанный текст
                if (helper.isTextContainForeignSymbol(line)) {
                    Box.flag = Constants.FOREIGN_SYMBOL_FOUND;
                    logger.error(String.format("Thread %s: Foreign symbol found!", Thread.currentThread().getName()));
                }

                String[] words = helper.split(line);

                // проверяем, не встречался ли слова в данной строке ранее.
                if (helper.isTextContainDuplicates(words, set)) {
                    Box.flag = Constants.DUPLICATE_FOUND;
                    logger.error(String.format("Thread %s: text contain duplicates!", Thread.currentThread().getName()));
                }
            }
        } catch (IOException e) {
            Box.flag = Constants.UNEXPECTED_ERROR;
        }

        logger.info("Reading completed!");

        return set;
    }
}

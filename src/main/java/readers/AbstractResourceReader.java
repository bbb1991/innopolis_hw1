package readers;

import helper.StringHelper;
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

    public Set<String> bufferReader(BufferedReader bufferedReader) {
        Set<String> set = new HashSet<>();
        try (
             Stream<String> stringStream = bufferedReader.lines()) {

            stringStream.forEach(e -> {
                if (helper.isTextContainForeignSymbol(e)) {
                    logger.error(String.format("Thread %s: Foreign symbol found!", Thread.currentThread().getName()));
                    throw new IllegalArgumentException(String.format(" имеет букву ин. языка!%n"));
                }

                String[] words = helper.split(e);

                if (helper.isTextContainDuplicates(words, set)) {
                    logger.error(String.format("Thread %s: text contain duplicates!", Thread.currentThread().getName()));
                    throw new IllegalArgumentException(String.format(" имеет дубликат!%n"));
                }

            });

        }

        logger.info("Reading completed!");

        return set;
    }
}

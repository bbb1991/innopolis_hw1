package readers;

import helper.StringHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * Created by bbb1991 on 11/3/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class FileBasedResourceReader extends AbstractResourceReader {

    FileBasedResourceReader(StringHelper helper) {
        super(helper);
    }

    @Override
    public Set<String> read(String file) {

        logger.info("Reading file: " + file );

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            return process(bufferedReader);

        } catch (IOException e) {
            logger.trace(String.format("Something terrible happened in thread %s!", Thread.currentThread().getName()), e);
            throw new RuntimeException(e);
        }
    }
}

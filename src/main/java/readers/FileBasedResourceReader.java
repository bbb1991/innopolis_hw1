package readers;

import helper.StringHelper;
import main.States;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import static main.States.*;

/**
 * Created by bbb1991 on 11/3/16.
 * Ридер для чтения файлов у которых не указан протокол.
 * примеры:
 * ./file.txt
 * /tmp/foo/bar/file.txt
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class FileBasedResourceReader extends AbstractResourceReader {

    FileBasedResourceReader(StringHelper helper) {
        super(helper);
    }

    @Override
    public Set<String> read(String file) {

        Set<String> set = Collections.emptySet();

        logger.info("Reading file: " + file );

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            set =  process(bufferedReader);

        } catch (IOException e) {
            States.setFlag(FILE_NOT_FOUND);
            logger.trace(String.format("Something terrible happened in thread %s!", Thread.currentThread().getName()), e);
        }

        return set;
    }
}

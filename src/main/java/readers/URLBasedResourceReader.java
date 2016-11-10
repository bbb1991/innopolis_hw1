package readers;

import helper.StringHelper;
import main.States;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Set;

import static main.States.*;

/**
 * Created by bbb1991 on 11/3/16.
 * Ридер для ресурсов у которых указан протокол
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
class URLBasedResourceReader extends AbstractResourceReader {
    URLBasedResourceReader(StringHelper helper) {
        super(helper);
    }

    @Override
    public Set<String> read(String resource) {

        Set<String> set = Collections.emptySet();

        logger.info("Opening url: " + resource);

        try {
            URL url = new URL(resource);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                set = process(reader);
            }

        } catch (Exception e) {
            States.setFlag(UNEXPECTED_ERROR);
        }

        return set;
    }
}

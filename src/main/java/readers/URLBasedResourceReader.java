package readers;

import helper.StringHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * Created by bbb1991 on 11/3/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
class URLBasedResourceReader extends AbstractResourceReader {
    URLBasedResourceReader(StringHelper helper) {
        super(helper);
    }

    @Override
    public Set<String> read(String resource) {

        logger.info("Opening url: " + resource);

        try {
            URL url = new URL(resource);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                return bufferReader(reader);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

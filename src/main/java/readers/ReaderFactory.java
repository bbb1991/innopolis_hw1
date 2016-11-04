package readers;

import helper.StringHelper;

import java.util.regex.Pattern;

/**
 * Created by bbb1991 on 11/3/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class ReaderFactory {

    private ReaderFactory() {}

    public static AbstractResourceReader getResourceReader(String resource) {

        StringHelper helper = new StringHelper();
        Pattern pattern = Pattern.compile("^(?:(ht|f)tp(s?)\\:\\/\\/)?[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&amp;%\\$#_]*)?$");

        if (pattern.matcher(resource).find()) { // // TODO: 11/4/16 improve checking resource type
            return new URLBasedResourceReader(helper);
        } else {
            return new FileBasedResourceReader(helper);
        }
    }
}

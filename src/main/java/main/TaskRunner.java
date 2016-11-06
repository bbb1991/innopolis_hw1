package main;

import helper.StringHelper;
import org.apache.log4j.Logger;
import readers.AbstractResourceReader;
import readers.ReaderFactory;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by bbb1991 on 11/3/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class TaskRunner implements Runnable {

    private static final Logger logger = Logger.getLogger(TaskRunner.class);
    private String resource;
    private final Box<String> box;

    public TaskRunner(final String resource, Box<String> box) {
        this.resource = resource;
        this.box = box;
    }

    @Override
    public void run() {
        logger.info(String.format("Запущен %s поток для ресурса: %s", Thread.currentThread().getName(), resource));

        AbstractResourceReader resourceReader = ReaderFactory.getResourceReader(resource);
        Set<String> set = resourceReader.read(resource);

        if (!box.addElements(set)) {
            Box.flag = Constants.DUPLICATE_FOUND;
            logger.error("Duplicate found!");
        }

        logger.info(String.format("Поток %s завершил свою работу", Thread.currentThread().getName()));

    }
}

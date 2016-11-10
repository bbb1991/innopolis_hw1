package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import readers.AbstractResourceReader;
import readers.ReaderFactory;

import java.util.Set;

import static main.States.DUPLICATE_FOUND;

/**
 * Created by bbb1991 on 11/3/16.
 * Класс для работы с потоком
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class TaskRunner implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(TaskRunner.class);

    /**
     * Ресурс, с которым будет работать поток
     */
    private String resource;

    /**
     * Бокс для аккумулирования результата
     */
    private final Box<String> box;

    // конструктор
    public TaskRunner(final String resource, Box<String> box) {
        this.resource = resource;
        this.box = box;
    }

    @Override
    public void run() {
        logger.info(String.format("Запущен %s поток для ресурса: %s", Thread.currentThread().getName(), resource));

        // получаем ридер через фабрику
        AbstractResourceReader resourceReader = ReaderFactory.getResourceReader(resource);

        // все необходимая работа с ресурсом проходит в ридере
        Set<String> set = resourceReader.read(resource);

        if (!box.addElements(set)) { // дубликат был найден в другом ресурсе.
            States.setFlag(DUPLICATE_FOUND);
            logger.error(String.format("Thread %s: Duplicate found!", Thread.currentThread().getName()));
        } else if (States.getFlag() == States.OK) { // рапортуем что все ОК
            logger.info(String.format("Поток %s завершил свою работу", Thread.currentThread().getName()));
        }
    }
}

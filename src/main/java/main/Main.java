package main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbb1991 on 11/2/16.
 * Класс, который и запускает все безобразие
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // Если в аргументе не было ничего передана
        if (args.length == 0) {
            logger.error("Вы должны подать список ресурсов!");
            System.exit(-1);
        }

        // по условию задачи каждый ресурс должен быть отработан в собственном потоке
        List<Thread> threads = new ArrayList<>();

        logger.info(String.format("Было передано %d ресурсов. Запускаю потоки...%n", args.length));

        // FIXME clean up all this mess
        Box<String> box = new Box<>(); // создаем бокс для хранения результатов
        try {
            for (String file : args) { // на каждый ресурс запускаем новый поток
                logger.info("Запускаю поток для ресурса: " + file);
                Thread thread = new Thread(new TaskRunner(file, box));
                threads.add(thread);
                thread.start();
            }

            // ожидаем завершения всех потоков
            for (Thread thread : threads) {
                thread.join();
            }

            // проверяем результат выполнения
            if (States.getFlag() == States.OK) {
                logger.info("Everything OK. Shutting down app.");
            } else {
                logger.error("Something wrong. Flag is: " + States.getFlag());
            }

        } catch (Exception ex) {
            logger.trace("Something  terrible happened! Abort mission! ", ex);
        }
    }
}

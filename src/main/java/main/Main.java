package main;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbb1991 on 11/2/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);



    public static void main(String[] args) {

        // Если в аргументе не было ничего передана
        if (args.length == 0) {
            logger.error("Вы должны подать список ресурсов!");
            System.exit(-1);
        }

        logger.info(String.format("Было передано %d ресурсов. Запускаю потоки...%n", args.length));

        List<Thread> threads = new ArrayList<>();


        // FIXME clean up all this mess
        Box<String> box = new Box<>();
        try {
            for (String file : args) {
                logger.info("Запускаю поток для ресурса: " + file);
                Thread thread = new Thread(new TaskRunner(file, box));
                threads.add(thread);
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

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

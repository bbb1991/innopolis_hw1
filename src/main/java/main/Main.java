package main;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        ExecutorService executorService = Executors.newFixedThreadPool(args.length);


        // FIXME clean up all this mess
        Box<String> box = new Box<>();
        try {
            for (String file : args) {
                logger.info("Запускаю поток для ресурса: " + file);
                executorService.execute(new TaskRunner(file, box));
            }

            if (Box.flag == Constants.SUCCESS) {
                logger.info("Everything OK. Shutting down app.");
            } else {
                logger.info("Something wrong. Flag is: " + Box.flag);
            }

        } catch (Exception ex) {
            executorService.shutdownNow();
            logger.trace("Something happened! Abort mission! ", ex);
        }
    }
}

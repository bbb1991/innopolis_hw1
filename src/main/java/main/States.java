package main;

import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by bbb1991 on 11/6/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public enum States {
    OK, DUPLICATE_FOUND, FOREIGN_SYMBOL_FOUND, FILE_NOT_FOUND, UNEXPECTED_ERROR;

    //private static final AtomicReference<States> atomicReference = new AtomicReference<>(OK);


    private static final Object obj = new Object();

    private static States flag = OK;

    public static void setFlag(States state) {
        //atomicReference.set(state);
        synchronized (obj) {
            flag = state;
        }
    }

    public static States getFlag() {
        //return atomicReference.get();
        return flag;
    }
}

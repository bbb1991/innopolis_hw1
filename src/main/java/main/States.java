package main;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by bbb1991 on 11/6/16.
 * Класс для хранения и работы с флагом состояния
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */

public enum States {

    OK, // все идет по плану
    DUPLICATE_FOUND, // найден дубликат слова
    FOREIGN_SYMBOL_FOUND, // найден символ/буква ин. языка
    FILE_NOT_FOUND, // файл не найден/путь до ресурса указан некорректно
    UNEXPECTED_ERROR; // иная ошибка

    /**
     * Общий ресурс для всех потоков для мониторинга что происходит в соседних потоках
     */
    private static final AtomicReference<States> flag = new AtomicReference<>(OK);


    /**
     * Метод для установления нового состояния
     * @param state новое состояние флаша
     */
    public static void setFlag(States state) {
        flag.set(state);
    }

    /**
     * метод геттер для получения состояния флага
     * @return состояние флага
     */
    public static States getFlag() {
        return flag.get();
    }
}

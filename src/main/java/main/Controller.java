package main;

/**
 * Created by bbb1991 on 11/10/16.
 *
 * Реализация интерфейса MBean. Позволяет с помощью jconsole узнавать сколько всего слов хранит множество
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class Controller implements ControllerMBean {

    /**
     * Бокс, которая хранит общий ресурс (множество) для всех потоков, где хранятся все считанные слова
     */
    private Box box;

    public Controller(Box box) {
        this.box = box;
    }

    /**
     * Метод возвращает сколько всего слов считано в общем
     * @return считанные уникальные слова
     */
    @Override
    public int getSetSize() {
        return box.getSetSize();
    }
}

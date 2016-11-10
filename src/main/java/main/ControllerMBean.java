package main;

/**
 * Created by bbb1991 on 11/10/16.
 * Интерфейс для реализаций JMX.
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public interface ControllerMBean {

    /**
     * Метод, который в консоль JMX будет выводить размер прочитанных слов.
     * @return сколько уникальных слов содержит в себе сет
     */
    int getSetSize();

}

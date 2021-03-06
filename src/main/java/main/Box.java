package main;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static main.States.*;

/**
 * Created by bbb1991 on 11/4/16.
 * Класс, предназначенный для хранения и сравнения на уникальность
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public final class Box<T> {
    private final Set<T> set;

    public Box() {
        this.set = new CopyOnWriteArraySet<>();
    }

    /**
     * Метод, который аккумулирует значение с разных потоков. Так же возвращает результат, все ли переданные значния
     * уникальны.
     *
     * @param collection коллекция, которую необходимо добавить в существующий множество
     * @return были ли переданные элементы уникальными.
     * <code>true</code> - если все элементы были уникальны
     * <code>false</code> - если какой то элемент уже существовал.
     */

    // TODO: 11/4/16 maybe should adding and checking logic separate?
    public boolean addElements(Collection<T> collection) {
        int size = this.set.size();
        this.set.addAll(collection);
        return size + collection.size() == this.set.size();
    }

    public int getSetSize() {
        return set.size();
    }

}

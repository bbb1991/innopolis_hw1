package main;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bbb1991 on 11/4/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class Box<T> {
    private final Set<T> set;
    private static final Object MONITOR = new Object();

    public Box() {
        this.set = new HashSet<T>();
    }

    /**
     * Метод, который аккумулирует значение с разных потоков. Так же возвращает результат, все ли значения были
     * @param collection
     * @return
     */
    public boolean addElements(Collection<T> collection) {
        synchronized (MONITOR) {
            int size = this.set.size();
            this.set.addAll(collection);
            return size + collection.size() == this.set.size();
        }
    }
}

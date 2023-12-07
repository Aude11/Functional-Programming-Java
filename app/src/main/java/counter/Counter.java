package counter;

import java.util.function.Predicate;

public class Counter<T extends Countable> implements Countable {
    private int count;

    public int getCount() {
        return count;
    }

    public void add(T item) {
        count += item.getCount();
    }

    public void add(T item, Predicate<T> predicate) {
        if (predicate.test(item)) {
            count += item.getCount();
        }
    }

    public Predicate<String> containsA = t -> t.contains("crayon");
}

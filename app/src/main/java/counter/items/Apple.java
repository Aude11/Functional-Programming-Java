package counter.items;

import counter.Countable;

import java.time.LocalDate;
import java.util.Comparator;

public record Apple(Colour colour, LocalDate datePicked, LocalDate bestBefore) implements Countable {

    public int getCount() {
        return 1;
    }
}

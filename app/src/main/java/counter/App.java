/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package counter;

import counter.items.Apple;
import counter.items.Box;
import counter.items.Cart;
import counter.items.Colour;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class App {
    public static void main(String[] argv) {
        // Some things to count
        List<Apple> someApples = Arrays.asList(
                new Apple(Colour.RED, LocalDate.of(2023, 3, 8), LocalDate.of(2023, 5, 4)),
                new Apple(Colour.RED, LocalDate.of(2023, 2, 10), LocalDate.of(2023, 6, 20)),
                new Apple(Colour.RED, LocalDate.of(2023, 1, 7), LocalDate.of(2023, 4, 18)),
                new Apple(Colour.YELLOW, LocalDate.of(2023, 3, 25), LocalDate.of(2023, 5, 11)),
                new Apple(Colour.YELLOW, LocalDate.of(2023, 2, 23), LocalDate.of(2023, 4, 16)),
                new Apple(Colour.GREEN, LocalDate.of(2023, 2, 12), LocalDate.of(2023, 3, 7)),
                new Apple(Colour.GREEN, LocalDate.of(2023, 2, 9), LocalDate.of(2023, 5, 9)),
                new Apple(Colour.GREEN, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 4, 10))
        );

        Box<Apple> boxOfApples = new Box<>();
        boxOfApples.add(new Apple(Colour.RED, LocalDate.of(2023, 3, 8), LocalDate.of(2023, 5, 4)));
        boxOfApples.add(new Apple(Colour.YELLOW, LocalDate.of(2023, 2, 23), LocalDate.of(2023, 4, 16)));

        Cart<Apple> cart = new Cart<>();
        cart.add(boxOfApples);

        System.out.println("Lambda Exercise Output:");
        // Add your lambda exercises here
        System.out.println(someApples);
        List<Apple> someApplesAnonymous = someApples;
        List<Apple> someApplesLambda = someApples;
        List<Apple> someApplesPrintFct = someApples;
        Collections.sort(someApplesAnonymous , new Comparator<Apple>() {
            public int compare(Apple apple1, Apple apple2) {
                int bestBefore = apple1.bestBefore().compareTo(apple2.bestBefore());
                return bestBefore;
            }
        });

        //System.out.println(someApplesAnonymous);
        someApplesAnonymous.forEach(apple-> System.out.println(apple));


        //Collections.sort(events, (e1, e2) -> Long.compare(e1.timestamp, e2.timestamp));
        Collections.sort(someApplesLambda, (apple1, apple2) -> apple1.bestBefore().compareTo(apple2.bestBefore()));

        System.out.println("someApplesLambda:");
        //System.out.println(someApplesLambda );
        someApplesLambda.forEach(apple->{
                    System.out.println(apple);
                });


        Comparator<Apple>[] comparators = new Comparator[3];
        Comparator<Apple> comparatorDatePicked = (apple1, apple2) -> apple1.datePicked().compareTo(apple2.datePicked());
        Comparator<Apple> comparatorBestBefore = (apple1, apple2) -> apple1.bestBefore().compareTo(apple2.bestBefore());
        Comparator<Apple> comparatorColour = (apple1, apple2) -> apple1.colour().name().compareTo(apple2.colour().name());
        comparators[0] = comparatorDatePicked;
        comparators[1] = comparatorBestBefore;
        comparators[2] = comparatorColour;
        System.out.println("Test fct:");
        printApples(someApplesPrintFct,comparators[1]);

        System.out.println("Streams Exercises Output:");
        // Add your stream exercises here
        //List<Apple> someApplesBck= someApples;
        //Stream<Apple> stream = Stream.of(someApplesBck);

        someApples.stream().forEach(s -> System.out.println(s));
        System.out.println("\n\n");
        someApples.stream().skip(3).forEach(s -> System.out.println(s));

        /*
       someApples.stream()
                .findFirst()
               .isPresent(System.out::println); */

       Optional<Apple> firstApple =  someApples.stream().findFirst();
       if (firstApple.isPresent()) {
           System.out.println(firstApple);
       }
       //
        //stream.reduce((first, second) -> second);
        //stream.forEach(s -> System.out.println(s));
        someApples.stream()
                .filter(apple -> apple.bestBefore()
                .isBefore( LocalDate.of(2023, 5, 4)))
                .forEach(s -> System.out.println(s));

        someApples.stream()
                .filter(apple -> apple.bestBefore()
                        .isBefore( LocalDate.of(2023, 5, 4)))
                .forEach(s -> System.out.println("There is a " + s.colour() + " apple that is best before " + s.bestBefore()));

        someApples.stream()
                .filter(apple -> apple.colour().equals(Colour.RED))
                .forEach(s -> System.out.println("There is a " + s.colour() + " apple that is best before " + s.bestBefore()));


        System.out.println("Predicate Exercises Output:");
        Counter<Apple> appleCounter = new Counter<>();
        someApples.forEach(appleCounter::add);

        System.out.println(appleCounter.getCount()); // Should be 8

        Counter<Cart<Apple>> cartCounter = new Counter<>();
        cartCounter.add(cart);

        System.out.println(cartCounter.getCount()); // Should be 2 (number of apples in the cart in total)

        Counter<Countable> anythingCounter = new Counter<>();
        someApples.forEach(anythingCounter::add);
        anythingCounter.add(cart);

        System.out.println(anythingCounter.getCount()); // Should be 10 - sum of the above
    }

    private static void printApples(List<Apple> apples,Comparator<Apple>  comparator ) {
        Collections.sort(apples, comparator);
        apples.forEach(apple->{
            System.out.println(apple);
        });
    }

}

package FunctionPredicateConsumerSupplier;

import FunctionPredicateConsumerSupplier.Main.Person.Gender;

import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {


    static Function<Integer, Integer> incrementByOne = number -> number + 1;
    static Function<Integer, Integer> multiplyBy10 = number -> number * 10;
    // Predicate : those function are the same
    static Function<Person, Boolean> isFemale = person -> person.gender.equals(Gender.FEMALE);

    static Predicate<Person> isFemalePredicate = person -> person.gender.equals(Gender.FEMALE);
    static BiPredicate<Person, Person> sameGender =
            (person, person2) -> person.gender.equals(person2.gender);

    static BiFunction<Integer, Integer, Integer> multiplyTwoNumbers =
            (number1, number2) -> number1 * number2;

    static Consumer<Person> sayHi = person -> System.out.println(
            "hi " + (person.gender.equals(Gender.FEMALE) ? "Ms " : "Mrs ") + person.name
    );
    static BiConsumer<Person, Boolean> sayHiV2 = (person, showTitle) -> System.out.println(
            "hi " + (showTitle ? (person.gender.equals(Gender.FEMALE) ? "Ms " : "Mrs ") : "")
                    + person.name
    );

    static Supplier<Number> getRandomNumber = () ->
            new Random().nextFloat();

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Ayman", Gender.MALE),
                new Person("Amin", Gender.MALE),
                new Person("Souad", Gender.FEMALE),
                new Person("Ihsan", Gender.FEMALE)
        );

        // Declarative approach
        people.stream()
//                .filter(person -> person.gender.equals(Gender.FEMALE))
//                .filter(person -> isFemale.apply(person))
                .filter(person -> isFemalePredicate.test(person))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println(
                incrementByOne
                        .andThen(multiplyBy10)
                        .apply(3)
        );
        System.out.println(
                multiplyTwoNumbers
                        .apply(3, 2)
        );
        sayHi.accept(people.get(0));
        sayHiV2.accept(people.get(0), false);

        System.out.println(sameGender.test(people.get(0), people.get(2)));

        System.out.println(getRandomNumber.get());
    }


    static class Person {
        public String name;
        public Gender gender;

        public Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }

        enum Gender {
            MALE,
            FEMALE
        }
    }
}

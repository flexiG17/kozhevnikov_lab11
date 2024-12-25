package Stream;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) throws IOException {
        List<Person> dataJson = FileLoader.readFile("src/main/java/Stream/data.json");

        System.out.println("\n--------------------Старше 30 лет (30 чел.)-------------------");
        dataJson.stream()
                .filter(person -> person.age > 30)
                .limit(30)
                .forEach(System.out::println);

        System.out.println("\n--------------------Сортировка по зарплате (30 чел.)-------------------");
        dataJson.stream()
                .sorted(Comparator.comparingInt(person -> person.salary))
                .limit(30)
                .forEach(System.out::println);

        System.out.println("\n--------------------Первые 5 записей-------------------");
        dataJson.stream()
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n--------------------Только имена профессией-------------------");
        dataJson.stream()
                .map(person -> person.job)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\n--------------------Топ10 максимальных зарплат для младше 25 лет в \"Праге\"-------------------");
        dataJson.stream()
                .filter(person -> person.age < 25 && "Прага".equals(person.city))
                .sorted((p1, p2) -> Integer.compare(p2.salary, p1.salary))
                .limit(10).map(person -> person.salary)
                .forEach(System.out::println);

        System.out.println("\n--------------------Количество с зарплатой > 50к по профессии \"Продавца\"-------------------");
        long countHighSalarySellers = dataJson.stream()
                .filter(person -> person.salary > 50000 && "Продавец".equals(person.job))
                .count();
        System.out.println(countHighSalarySellers);

        System.out.println("\n--------------------Максимальная зарплата среди 18–25 лет в \"Москве\"-------------------");
        OptionalInt maxSalaryInMoscow
                = dataJson.stream()
                .filter(person -> person.age >= 18 && person.age <= 25 && "Москва".equals(person.city))
                .mapToInt(person -> person.salary)
                .max();
        maxSalaryInMoscow.ifPresent(System.out::print);

        System.out.println("\n--------------------Минимальный возраст с зарплатой > 100к в \"Праге\"-------------------");
        OptionalInt minAgeInPrague
                = dataJson.stream()
                .filter(person -> person.salary > 100000 && "Прага".equals(person.city))
                .mapToInt(person -> person.age).min();
        minAgeInPrague.ifPresent(System.out::println);

        System.out.println("\n--------------------Группировка по профессии-------------------");
        dataJson.stream()
                .collect(Collectors.groupingBy(person -> person.job))
                .forEach((job, persons) -> System.out.println(job + ": " + persons.size()));

        System.out.println("\n--------------------Количество каждой профессии-------------------");
        dataJson.stream()
                .collect(Collectors.groupingBy(person -> person.job, Collectors.counting()))
                .forEach((job, count) -> System.out.println(job + ": " + count));

        System.out.println("\n--------------------Максимальные зарплаты по городам-------------------");
        dataJson.stream()
                .collect(Collectors.groupingBy(person -> person.city, Collectors.maxBy(Comparator.comparingInt(person -> person.salary))))
                .forEach((city, person) -> System.out.println(city + ": " + person.map(p -> p.salary).orElse(0)));

        System.out.println("\n--------------------Средняя зарплата по профессиям-------------------");
        dataJson.stream()
                .collect(Collectors.groupingBy(person -> person.job, Collectors.averagingInt(person -> person.salary)))
                .forEach((job, avgSalary) -> System.out.println(job + ": " + avgSalary));
    }
}

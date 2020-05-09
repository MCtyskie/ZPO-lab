package com.mycompany.mavenproject11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class MyMethodsClass {

    //cena pizzy podanej w argumencie, aby oszczędzieć kilka linijek kodu
    private static Integer getPizzaPrice(Pizza p) {
        return p.getIngredients()
                .stream()
                .mapToInt(i -> i.getPrice())
                .sum();
    }
    
    public static Pizza findCheapestSpicy(List<Pizza> pizzas) {
        return pizzas.stream()
                .filter(p -> p.getIngredients()
                    .stream()
                    .anyMatch(i -> i.isSpicy()))
                .min((p1, p2) -> p1.getIngredients().stream().mapToInt(i -> i.getPrice()).sum()
                                - p2.getIngredients().stream().mapToInt(i -> i.getPrice()).sum())
                .orElse(null);
    }

    public static Pizza findMostExpensiveVegeterian(List<Pizza> pizzas) {
        return pizzas.stream()
                .filter(p -> p.getIngredients()
                    .stream()
                    .allMatch(i -> !i.isMeat()))
                .max(Comparator.comparing(p -> getPizzaPrice(p)))
                .get();
    }

    public static List<Pizza> iLikeMeat(List<Pizza> pizzas) {
        return pizzas.stream()
                .filter(p -> p.getIngredients()
                    .stream()
                    .anyMatch(i -> i.isMeat()))
                .sorted(Comparator.comparing(p -> p.getIngredients()
                    .stream()
                    .filter(Ingredients::isMeat)
                    .count(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public static Map<Integer, List<Pizza>> groupByPrice(List<Pizza> pizzas) {
        return pizzas.stream()
                .collect(Collectors.groupingBy(p -> getPizzaPrice(p)));
    }

    public static String formatedMenu(List<Pizza> pizzas) {
        return pizzas.stream()
                .map(p -> String.format("%s: %s- %d zł",
                    p.getName(),
                    p.getIngredients()
                        .stream()
                        .map(i -> i.getName()).collect(Collectors.joining(", ")),
                    getPizzaPrice(p)))
                .collect(Collectors.joining("\n"));
    }

}

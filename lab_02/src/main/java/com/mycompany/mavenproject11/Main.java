package com.mycompany.mavenproject11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {

    /**
     * 1. Pizza findCheapestSpicy()- metoda zwracająca najtańszą strą pizzę. 
     * 2. Pizza findMostExpensiveVegeterian()- metoda zwracająca najdroższą pizzę
     * wegetariańską. 3. List<Pizza> iLikeMeat() - metoda zwracająca same pizze
     * mięsne, posortowane malejąco po liczbie składników mięsnych. 4.
     * Map<Integer, List<Pizza>> groupByPrice() - metoda grupujące pizze po
     * cenie. 5. String formatedMenu() - metoda zwracająca string w pozstaci
     * nazwa_pizzy: składnik1, składnik2, składnik3 - cena, kolejne pizze
     * oddzielone znakiem nowej linii.
     */
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MyMethodsClass mmc = new MyMethodsClass();
        List<Pizza> pizzas = Arrays.asList(Pizza.values());

        System.out.println("Wybierz opcję:");
        System.out.println("1. Najtańsza ostra pizza");
        System.out.println("2. Najdroższa pizza wegetariańska");
        System.out.println("3. Wszystkie pizze mięsne");
        System.out.println("4. Grupowanie pizz po cenie");
        System.out.println("5. Sformatowane menu");
        System.out.println("--------------------------------------------");
        System.out.print("Twój wybór: ");

        Boolean x = true;
        while (x) {
            int choice = 0;
            try {
                choice = Integer.valueOf(br.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch (choice) {
                case 1:
                    mmc.findCheapestSpicy(pizzas);
                    System.out.println(mmc.findCheapestSpicy(pizzas).toString());
                    break;
                case 2:
                    mmc.findMostExpensiveVegeterian(pizzas);
                    System.out.println(mmc.findMostExpensiveVegeterian(pizzas).toString());
                    break;
                case 3:
                    mmc.iLikeMeat(pizzas);
                    System.out.println(mmc.iLikeMeat(pizzas).toString());
                    break;
                case 4:
                    mmc.groupByPrice(pizzas);
                    System.out.println(mmc.groupByPrice(pizzas).toString());
                    break;
                case 5:
                    mmc.formatedMenu(pizzas);
                    System.out.println(mmc.formatedMenu(pizzas).toString());
                    break;
                default:
                    System.out.println("Błędna liczba!");
                    x = false;
                    break;
            }
        }
    }

}

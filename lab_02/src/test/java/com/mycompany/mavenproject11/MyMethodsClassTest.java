/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject11;

import static com.mycompany.mavenproject11.Pizza.AMORE;
import static com.mycompany.mavenproject11.Pizza.CALABRESE;
import static com.mycompany.mavenproject11.Pizza.CAPRESE;
import static com.mycompany.mavenproject11.Pizza.CAPRI;
import static com.mycompany.mavenproject11.Pizza.CARUSO;
import static com.mycompany.mavenproject11.Pizza.FARMER;
import static com.mycompany.mavenproject11.Pizza.FOUR_CHEESE;
import static com.mycompany.mavenproject11.Pizza.HAVAI;
import static com.mycompany.mavenproject11.Pizza.MAMA_MIA;
import static com.mycompany.mavenproject11.Pizza.MARGHERITA;
import static com.mycompany.mavenproject11.Pizza.SOPRANO;
import static com.mycompany.mavenproject11.Pizza.TABASCO;
import static com.mycompany.mavenproject11.Pizza.VEGETARIANA;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author win10
 */
public class MyMethodsClassTest {

    /**
     * Test of findCheapestSpicy method, of class MyMethodsClass.
     */
    @org.junit.jupiter.api.Test
    public void testFindCheapestSpicy() {
        System.out.println("findCheapestSpicy");
        List<Pizza> pizzas=Arrays.asList(TABASCO, CARUSO, CAPRESE, SOPRANO);
        Pizza expResult = CARUSO;
        Pizza result = MyMethodsClass.findCheapestSpicy(pizzas);
        assertEquals(expResult, result);
    }

    /**
     * Test of findMostExpensiveVegeterian method, of class MyMethodsClass.
     */
    
    @org.junit.jupiter.api.Test
    public void testFindMostExpensiveVegeterian() {
        System.out.println("findMostExpensiveVegeterian");
        List<Pizza> pizzas=Arrays.asList(MARGHERITA,HAVAI,VEGETARIANA,CALABRESE);
        Pizza expResult = VEGETARIANA;
        Pizza result = MyMethodsClass.findMostExpensiveVegeterian(pizzas);
        assertEquals(expResult, result);
    }

    /**
     * Test of iLikeMeat method, of class MyMethodsClass.
     */
    
    @org.junit.jupiter.api.Test
    public void testILikeMeat() {
        System.out.println("iLikeMeat");
        List<Pizza> pizzas=Arrays.asList(TABASCO,MARGHERITA,HAVAI,VEGETARIANA,CALABRESE,FARMER,AMORE);
        List<Pizza> expResult = Arrays.asList(TABASCO,CALABRESE,FARMER,HAVAI,AMORE);
        List<Pizza> result = MyMethodsClass.iLikeMeat(pizzas);
        assertEquals(expResult, result);
    }

    
    /**
     * Test of groupByPrice method, of class MyMethodsClass.
     */
    @org.junit.jupiter.api.Test
    public void testGroupByPrice() {
        System.out.println("groupByPrice");
        List<Pizza> pizzas=Arrays.asList(CAPRI,HAVAI,MAMA_MIA,VEGETARIANA);
        Map<Integer, List<Pizza>> expResult = new HashMap<>();
        expResult.put(17,List.of(CAPRI,HAVAI));
        expResult.put(18,List.of(MAMA_MIA));
        expResult.put(20,List.of(VEGETARIANA));
        Map<Integer, List<Pizza>> result = MyMethodsClass.groupByPrice(pizzas);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatedMenu method, of class MyMethodsClass.
     */
    @org.junit.jupiter.api.Test
    public void testFormatedMenu() {
        System.out.println("formatedMenu");
        List<Pizza> pizzas = Arrays.asList(MARGHERITA,CAPRI, HAVAI);
        String expResult="Marrgherita: cienkie ciasto, sos pomidorowy, ser- 13 zł\n"+
                         "Capri: cienkie ciasto, sos pomidorowy, ser, szynka, pieczarki- 17 zł\n"+
                         "Havai: cienkie ciasto, sos pomidorowy, ser, szynka, ananas- 17 zł";
        String result=MyMethodsClass.formatedMenu(pizzas);
        assertEquals(expResult, result);
    }
    
}

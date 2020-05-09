
package com.mycompany.mavenproject9;

import java.util.List;
import java.util.Optional;


public interface EmployeeDAO {
    //Zwraca pracownika o podanym id
    Optional<Employee> findOne(Integer id);
    //zwraca wszystkich pracowników
    List<Employee> findAll();
    //zwraca pracownika o podanym nazwisku
    Optional<Employee> findByName(String name);
    //usuwa pracownika
    void delete(Employee employee);
    //dodaje pracownika jesli nie istnieje lub aktualizuje jego dane jesli istnieje
    void save(Employee employee);
}

package com.mycompany.mavenproject14;

import static com.mycompany.mavenproject14.DayEnum.*;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

public class CClass {

    @Named(value = "String3")
    private String name;
    @Named(value = "Enum3")
    private DayEnum day;
    @Named(value = "Date3")
    private LocalDate date;

    public CClass() {
        this.name = "Bydgoszcz";
        this.day = MONDAY;
        this.date = LocalDate.of(2010, 12, 14);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DayEnum getDay() {
        return day;
    }

    public void setDay(DayEnum day) {
        this.day = day;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

package com.mycompany.mavenproject14;

import static com.mycompany.mavenproject14.DayEnum.MONDAY;
import java.time.LocalDate;

public class AClass {

    @Named(value = "String1")
    private String name;
    @Named(value = "Enum1")
    private DayEnum day;
    @Named(value = "Date1")
    private LocalDate date;

    public AClass() {
        this.name = "Michał";
        this.day = MONDAY;
        this.date = LocalDate.now();
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

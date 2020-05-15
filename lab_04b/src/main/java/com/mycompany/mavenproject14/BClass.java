package com.mycompany.mavenproject14;

import static com.mycompany.mavenproject14.DayEnum.THURSDAY;
import java.time.LocalDate;

public class BClass {

    @Named(value="String2")
    private String name;
    @Named(value="Enum2")
    private DayEnum day;
    @Named(value="Date2")
    private LocalDate date;

    public BClass() {
        this.name = "Telewizor";
        this.day = THURSDAY;
        this.date = LocalDate.of(2019, 05, 12);
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

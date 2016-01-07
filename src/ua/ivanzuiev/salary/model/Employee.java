package ua.ivanzuiev.salary.model;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by Иван on 30.11.2015.
 */
public class Employee {

    private String name;
    private BigDecimal salary;
    private GregorianCalendar birthDay;
    private GregorianCalendar hireDay;



    public Employee(String name, BigDecimal salary, GregorianCalendar birthDay) {
        this.name = name;
        this.salary = salary;
        this.birthDay = birthDay;
        hireDay=new GregorianCalendar();

    }

    //конструктор искпользуется для снятия менеджера с должности
    public Employee(Employee e){
        this.name=e.getName();
        this.salary=e.getSalary();
        this.birthDay=e.getBirthDay();
        this.hireDay=e.getHireDay();
    }

    public GregorianCalendar getHireDay() {
        return hireDay;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public GregorianCalendar getBirthDay() {
        return birthDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }


}

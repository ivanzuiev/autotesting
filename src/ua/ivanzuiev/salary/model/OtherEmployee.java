package ua.ivanzuiev.salary.model;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by Иван on 05.12.2015.
 */
public class OtherEmployee extends Employee {

    public OtherEmployee(String name, BigDecimal salary, GregorianCalendar birthDay){
        super(name, salary, birthDay);
    }

    public OtherEmployee(String name, BigDecimal salary, GregorianCalendar birthDay, String description){
        super(name, salary, birthDay);
        descriptionOfPosition=description;
    }

    //поле описывает должность сотрудника
    private  String descriptionOfPosition;

    public String getDescriptionOfPosition() {
        return descriptionOfPosition;
    }

    public void setDescriptionOfPosition(String descriptionOfPosition) {
        this.descriptionOfPosition = descriptionOfPosition;
    }
}

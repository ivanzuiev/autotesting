package ua.ivanzuiev.salary.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Иван on 05.12.2015.
 */
public class Manager extends Employee {

    public Manager(String name, BigDecimal salary, GregorianCalendar birthDay, ArrayList<Employee> list){
        super(name, salary, birthDay);
        subordinateEmployees=list;
    }

    public Manager(Employee employee){
        super(employee);
    }

    //лист подчиненных сотрудников
    private ArrayList<Employee> subordinateEmployees;

    public ArrayList<Employee> getSubordinateEmployees() {
        return subordinateEmployees;
    }
    public void deleteAllSubourdinateEmployees(){
        subordinateEmployees.clear();
    }

    public void addSubordinateEmployee(Employee employee){
        if(employee.equals(this))return;
        subordinateEmployees.add(employee);
    }

    public void addSubordinateEmployees(ArrayList<Employee> employees){
        for(Employee e:employees){
            addSubordinateEmployee(e);
        }
    }

    public boolean deleteSubordinateEmployee(Employee employee){
        return subordinateEmployees.remove(employee);
    }
}

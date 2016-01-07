package ua.ivanzuiev.salary.service;

import ua.ivanzuiev.salary.model.Employee;

import java.util.Comparator;

/**
 * Created by ���� on 06.12.2015.
 */
public class SurnameComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

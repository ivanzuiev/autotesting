package ua.ivanzuiev.salary.repository;

import ua.ivanzuiev.salary.model.Employee;
import ua.ivanzuiev.salary.model.Section;

/**
 * Created by Ivan on 05.12.2015.
 */
public interface EmployeeDAO {

    public void insertEmployee(Employee employee, Section department);
    public boolean deleteEmployee(Employee employee);
    public int findEmployee(String nameOfEmployee);
    public boolean updateEmployee(Employee oldEmployee, Employee newEmployee);
    public Employee[] allEmployeesOfCertainSection(Section  section);
    public Employee[] allEmployeesOfCompany();
    public Employee getEmployee(int index);




}

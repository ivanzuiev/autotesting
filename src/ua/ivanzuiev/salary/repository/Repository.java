package ua.ivanzuiev.salary.repository;

import ua.ivanzuiev.salary.model.Employee;
import ua.ivanzuiev.salary.model.EmployeeWrapper;
import ua.ivanzuiev.salary.model.Section;

import java.util.ArrayList;

/**
 * Created by Иван on 06.12.2015.
 */
public class Repository implements EmployeeDAO {

    private Repository(){

        allEmployeesOfCompany=new ArrayList<>();

        for(Employee employee:Employees.sectionA){
            allEmployeesOfCompany.add(new EmployeeWrapper(Section.PRODUCTON, employee));
        }
        for(Employee employee:Employees.sectionB){
            allEmployeesOfCompany.add(new EmployeeWrapper(Section.MARKETING, employee));
        }
        for(Employee employee:Employees.sectionC){
            allEmployeesOfCompany.add(new EmployeeWrapper(Section.TESTING, employee));
        }
    };

    private static Repository instance;

    public static final String MARKETING_DEPARTMENT="marketing";
    public static final String PRODUCTION_DEPARTMENT="production";
    public static final String TESTING_DEPARTMENT="testing";

    ArrayList<EmployeeWrapper> allEmployeesOfCompany;

    public static Repository getInstance(){
        if(instance==null) instance = new Repository();
        return instance;
    }

    public void insertEmployee(Employee employee, Section department) {
       allEmployeesOfCompany.add(new EmployeeWrapper(department, employee));
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        for(int i=0;i<allEmployeesOfCompany.size(); i++) {
        if(allEmployeesOfCompany.get(i).getEmployee().equals(employee))
            return deleteEmployee(i);
        }
        return false;
    }

    private boolean deleteEmployee(int index) {
       return allEmployeesOfCompany.remove(index)!=null;
    }

    @Override
    public int findEmployee(String nameOfEmployee){
    for(int i=0;i<allEmployeesOfCompany.size(); i++) {
        if(allEmployeesOfCompany.get(i).getEmployee().getName().equals(nameOfEmployee)) return i;
    }
        return -1;
    }



    @Override
    public boolean updateEmployee(Employee oldEmployee, Employee newEmployee) {

        for(int i=0;i<allEmployeesOfCompany.size(); i++) {
            if(allEmployeesOfCompany.get(i).getEmployee().equals(oldEmployee)) {
                allEmployeesOfCompany.get(i).setEmployee(newEmployee);
                return true;
            }
        }return false;

    }

    @Override
    public Employee[] allEmployeesOfCertainSection(Section section) {
        ArrayList<Employee> employees= new ArrayList<>();
        for(EmployeeWrapper ew:allEmployeesOfCompany){
            if(ew.getSection().equals(section))employees.add(ew.getEmployee());
        }

        Employee[] employeesArr= new Employee[employees.size()];
        return employees.toArray(employeesArr);
    }

    @Override
    public Employee[] allEmployeesOfCompany() {
        ArrayList<Employee> employees= new ArrayList<>();
        for(EmployeeWrapper ew:allEmployeesOfCompany){
           employees.add(ew.getEmployee());
        }
        Employee[] employeesArr= new Employee[employees.size()];
        return employees.toArray(employeesArr);
    }

    @Override
    public Employee getEmployee(int index) {
        return allEmployeesOfCompany.get(index).getEmployee();
    }
}

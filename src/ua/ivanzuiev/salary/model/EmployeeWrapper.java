package ua.ivanzuiev.salary.model;

/**
 * Created by Ivan on 20.12.2015.
 */
public class EmployeeWrapper {

    private Employee employee;
    private Section section;

    public EmployeeWrapper(Section section, Employee employee) {
        this.section = section;
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}

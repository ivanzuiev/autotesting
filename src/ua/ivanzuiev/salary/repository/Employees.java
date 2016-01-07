package ua.ivanzuiev.salary.repository;

import ua.ivanzuiev.salary.model.Employee;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Иван on 30.11.2015.
 * Каждая из констант этого класса - массив сотрудников отдела
 *
 */
public class Employees {

    private Employees(){};

    public final static Employee[] sectionA={
            new Employee("Stepanov Aleksey Dmitrievich", new BigDecimal(1000.00), new GregorianCalendar(1980, Calendar.NOVEMBER,25)),
            new Employee("Timoshenko Olga Pavlovna", new BigDecimal(1500.00), new GregorianCalendar(1984, Calendar.APRIL, 15)),
            new Employee("Melashenko Artem Viktorivich", new BigDecimal(2000.00), new GregorianCalendar(1985, Calendar.DECEMBER,10)),
            new Employee("Nikinova Elena Petrovna", new BigDecimal(1200.00), new GregorianCalendar(1989, Calendar.FEBRUARY, 5)),
            new Employee("Tretiakov Dmitriy Ivanovich", new BigDecimal(1400.00), new GregorianCalendar(1982, Calendar.DECEMBER, 7)),
           };

    public final static Employee[] sectionB={
             new Employee("Stepanova Olga Dmitrievna", new BigDecimal(2000.00), new GregorianCalendar(1980, Calendar.AUGUST,25)),
            new Employee("Timoshenko Oleg Pavlovich", new BigDecimal(2500.00), new GregorianCalendar(1984, Calendar.APRIL, 15)),
            new Employee("Melashenko Elena Nikolaevna", new BigDecimal(3000.00), new GregorianCalendar(1985, Calendar.DECEMBER,10)),
            new Employee("Nikiforova Elena Ivanovna", new BigDecimal(4200.00), new GregorianCalendar(1989, Calendar.FEBRUARY, 5)),
            new Employee("Yanukovich Dmitriy Kirilovich", new BigDecimal(2400.00), new GregorianCalendar(1982, Calendar.DECEMBER, 7))
    };

    public final static Employee[] sectionC={
            new Employee("Nikonenko Aleksey Petrovich", new BigDecimal(1650.00), new GregorianCalendar(1979, Calendar.FEBRUARY, 15)),
            new Employee("Trutenko Daryna Nikolaevna", new BigDecimal(1300.00), new GregorianCalendar(1972, Calendar.DECEMBER, 7)),
            new Employee("Stepchenko Natalia Dmitrievna", new BigDecimal(2100.00), new GregorianCalendar(1983, Calendar.AUGUST,2)),
            new Employee("Timoshenko Miroslav Pavlovich", new BigDecimal(2250.00), new GregorianCalendar(1984, Calendar.APRIL, 5)),
            new Employee("Melnik Elena Viktorovna", new BigDecimal(1800.00), new GregorianCalendar(1980, Calendar.DECEMBER,1)),
             };



}

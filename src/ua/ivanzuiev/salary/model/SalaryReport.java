package ua.ivanzuiev.salary.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class SalaryReport {

    private SalaryReport(){};


    public static BigDecimal[] salaryCalculationProportionally(Employee[] section, BigDecimal fondSalary){

        BigDecimal fondVariable=fondSalary;

        BigDecimal[] salaries=new BigDecimal[section.length];

        BigDecimal sumOfSalary=new BigDecimal("0.00");//переменная для поверки достаточности фонда для начисления зп

        boolean[] birthDayBonus=birthDayBonus(section);

        for(int i=0; i<salaries.length; i++){

            BigDecimal employeeSalary=section[i].getSalary();
            salaries[i]=employeeSalary;
            sumOfSalary=sumOfSalary.add(employeeSalary);
            fondVariable=fondVariable.subtract(employeeSalary);
            if(section[i]instanceof Manager){
                Manager manager = (Manager)section[i];
                BigDecimal managerBonus=(BigDecimal.valueOf(manager.getSubordinateEmployees().size())).multiply(new BigDecimal("20.00"));
                salaries[i].add(managerBonus);
                sumOfSalary=sumOfSalary.add(managerBonus);
                fondVariable=fondVariable.subtract(managerBonus);
            }

            //начисление бонусов за день рожденья
            if(birthDayBonus[i]==true){
                salaries[i]=salaries[i].add(new BigDecimal(200.00));
                fondVariable=fondVariable.subtract(new BigDecimal(200.00));
                sumOfSalary=sumOfSalary.add(new BigDecimal(200.00));
                fondVariable=fondVariable.subtract(new BigDecimal(200.00));
            }
        }

        //проверка достаточности фонда для начисления зп
        if(sumOfSalary.doubleValue()>fondSalary.doubleValue()){
            System.out.println("Salary fund is insufficient! Please, change the amount of salary fund!");
            System.out.println("The amount should be at least "+sumOfSalary);
        }else {

            //вычиление коэффициента для начисления премии пропорционально
            BigDecimal[] factor = new BigDecimal[section.length];
            for (int i = 0; i < section.length; i++) {
                factor[i] = section[i].getSalary().divide(sumOfSalary, 2, BigDecimal.ROUND_HALF_EVEN);

            }

            for (int i = 0; i < section.length; i++) {
                salaries[i] = salaries[i].add(factor[i].multiply(fondVariable)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
//                System.out.println(section[i].getName() + " " + salaries[i].toString());
            }
        }
        return salaries;
    }


    public static BigDecimal[] salaryCalculationEvenly(Employee[] section, BigDecimal fondSalary){

        BigDecimal fondVariable=fondSalary;

        BigDecimal[] salaryOfSection=new BigDecimal[section.length];

        BigDecimal sumOfSalary=new BigDecimal("0.00");

        boolean[] birthDayBonus=birthDayBonus(section);

        for(int i=0; i<salaryOfSection.length; i++){
            salaryOfSection[i]=section[i].getSalary();
            sumOfSalary=sumOfSalary.add(section[i].getSalary());
            fondVariable=fondVariable.subtract(section[i].getSalary());
            if(section[i]instanceof Manager){
                Manager manager = (Manager)section[i];
                BigDecimal managerBonus=(BigDecimal.valueOf(manager.getSubordinateEmployees().size())).multiply(new BigDecimal("20.00"));
                salaryOfSection[i].add(managerBonus);
                sumOfSalary=sumOfSalary.add(managerBonus);
                fondVariable=fondVariable.subtract(managerBonus);
            }

            if(birthDayBonus[i]==true){
                salaryOfSection[i]=salaryOfSection[i].add(new BigDecimal("200.00"));
                fondVariable=fondVariable.subtract(new BigDecimal("200.00"));
                sumOfSalary=sumOfSalary.add(new BigDecimal(200.00));
                fondVariable=fondVariable.subtract(new BigDecimal(200.00));
            }
        }

        if(sumOfSalary.doubleValue()>fondSalary.doubleValue()){
            System.out.println("Salary fund is insufficient! Please, change the amount of salary fund!");
            System.out.println("The amount should be at least "+sumOfSalary);
        }else {
            BigDecimal bonus = fondVariable.divide(new BigDecimal(new Integer(section.length).toString()), 2, BigDecimal.ROUND_HALF_EVEN);

            for (int i = 0; i < salaryOfSection.length; i++) {
                salaryOfSection[i] = salaryOfSection[i].add(bonus);
//                System.out.println(section[i].getName() + " " + salaryOfSection[i].toString());
            }
        }
        return salaryOfSection;
    }

    //массив наличия у сотрудника др в текущем месяце
    private static boolean[] birthDayBonus(Employee[] employees){
        boolean[] result=new boolean[employees.length];
        GregorianCalendar now=new GregorianCalendar();
        for (int i=0;i<employees.length;i++) {
            if (employees[i].getBirthDay().get(Calendar.MONTH) == now.get(Calendar.MONTH)) result[i] = true;
        }
        return result;
    }
}

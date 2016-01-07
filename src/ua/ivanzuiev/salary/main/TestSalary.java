package ua.ivanzuiev.salary.main;


import ua.ivanzuiev.salary.model.*;
import ua.ivanzuiev.salary.repository.Employees;
import ua.ivanzuiev.salary.repository.Repository;
import ua.ivanzuiev.salary.service.HireDayComparator;
import ua.ivanzuiev.salary.service.SurnameComparator;

import java.io.*;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TestSalary {


    public static void main(String[] args) {

        MainSalary ms=MainSalary.getInstance();

        //создаем сотрудника (все данные вводятся с консоли и проверяются через regExp)
//        Employee employee=ms.createEmployee();

        //назначаем нового сотрудника менеджером и назначаем ему в подчинение маркетинговый отдел сотрудников
//        Manager manager=ms.appointManager(employee);
//        ms.addEmployeeToMarketingDepartment(manager);
//        manager.addSubordinateEmployees(ms.allEmployeesOfMarketing());


        //рассчитываем зп для сотрудников согласно файлу config.txt
        //результат смотреть в файле salary.txt
        //правила формирования config.txt смотреть в readme.txt
        ms.parseConfigAndPerform();


        }

    }

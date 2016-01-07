package ua.ivanzuiev.salary.main;

import ua.ivanzuiev.salary.model.*;
import ua.ivanzuiev.salary.repository.EmployeeDAO;
import ua.ivanzuiev.salary.repository.Employees;
import ua.ivanzuiev.salary.repository.Repository;
import ua.ivanzuiev.salary.service.HireDayComparator;
import ua.ivanzuiev.salary.service.SurnameComparator;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainSalary {

    private EmployeeDAO rep;
    private static MainSalary instance;

    private MainSalary(){
        rep=Repository.getInstance();
    }

    public static MainSalary getInstance(){
        if(instance==null) instance = new MainSalary();
        return instance;
    }

    //метод создает обьект сотрудника из данных, введенных из консоли
    public Employee createEmployee() {
        Scanner in = new Scanner(System.in);
        String namePattern = "(([A-Z]){1}([a-z])+ ?){3}";
        String datePattern = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
        String salaryPattern = "[0-9.]+";
        Pattern patternOfName = Pattern.compile(namePattern);
        Pattern patternOfDate = Pattern.compile(datePattern);
        Pattern patternOfSalary = Pattern.compile(salaryPattern);
        String name;
        GregorianCalendar birthDay;
        BigDecimal salary;

        while (true) {
            System.out.println("Enter name, middlename and surname of employee in latin alphabet:");
            String inputName = in.nextLine();
            if (inputName == null || inputName.equals("")) {
                System.out.println("Invalid input!");
                continue;
            }

            Matcher matcher1 = patternOfName.matcher(inputName);
            if (matcher1.matches()) {
                name=inputName;
                System.out.println("Enter Birthday in dd/mm/yyyy format:");
                String inputDateOfBirth = in.nextLine();
                if (inputDateOfBirth == null || inputDateOfBirth.equals("")) {
                    System.out.println("Invalid input!");
                    continue;
                }
                Matcher matcher2 = patternOfDate.matcher(inputDateOfBirth);
                if (matcher2.matches()) {
                    String[] strArr=inputDateOfBirth.split("/");
                    birthDay=new GregorianCalendar(Integer.parseInt(strArr[0]),Integer.parseInt(strArr[1])-1, Integer.parseInt(strArr[2]));
                    System.out.println("Enter salary:");
                    String inputSalary = in.nextLine();

                    if (inputDateOfBirth == null || inputDateOfBirth.equals("")) {
                        System.out.println("Invalid input!");
                        continue;
                    }
                    Matcher matcher3 = patternOfSalary.matcher(inputSalary);
                    if (matcher3.matches()) {
                        salary=BigDecimal.valueOf(Double.parseDouble(inputSalary));
                        System.out.println("employee created!");
                        break;
                    } else {
                        System.out.println("Invalid input!");
                        continue;
                    }
                } else {
                    System.out.println("Invalid input!");
                    continue;
                }

            } else {
                System.out.println("Invalid input!");
                continue;
            }



        }

        return new Employee(name,salary,birthDay);
    }


    //методы добавляют сотрудника в соответствующий отдел
    public void addEmployeeToProductionDepartment(Employee employee){ rep.insertEmployee(employee, Section.PRODUCTON); }

    public void addEmployeeToMarketingDepartment(Employee employee){
        rep.insertEmployee(employee, Section.MARKETING);
    }

    public void addEmployeeToTestingDepartment(Employee employee){
        rep.insertEmployee(employee, Section.TESTING);
    }

    public int findIdOfEmployee(String name){
        String namePattern = "(([A-Z]){1}([a-z])+ ?){3}";
        Pattern patternOfName = Pattern.compile(namePattern);
        Matcher matcher = patternOfName.matcher(name);
        if(matcher.matches()){
            return rep.findEmployee(name);
        }else return -1;
    }

    public Employee findEmployee(int index){
        return  rep.getEmployee(index);
    }

    public Manager appointManager(Employee e){
        return new Manager(e);
    }

//    метод снимает менеджера с должности
//    @param removedManager - менеджер, снимаемый с должности
//    @param newManager - менеджер, которому назначаються подчиненные менеджера, снимаемого с должности

    public boolean removeManagerPosition(Manager removedManager, Manager newManager){
        ArrayList<Employee> employees=removedManager.getSubordinateEmployees();
        for(Employee e:employees){
            newManager.addSubordinateEmployee(e);
        }
        return rep.updateEmployee(removedManager,newManager);
    }

    private void calculateSalary(Employee[] employees, Comparator<Employee> howToSort, BonusType typeOfBonusCalculation, BigDecimal salaryFund, Locale locale) throws Exception {
        Arrays.sort(employees,howToSort);
        BigDecimal[] salaries=new BigDecimal[employees.length];
        if(typeOfBonusCalculation.equals(BonusType.EVENLY)){
            salaries=SalaryReport.salaryCalculationEvenly(employees,salaryFund);
        }
        else if(typeOfBonusCalculation.equals(BonusType.PROPORTIONALLY)){
            salaries=SalaryReport.salaryCalculationProportionally(employees,salaryFund);
        }
        File salary=new File("salary.txt");
        if(!salary.exists()) try {
            salary.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(new FileWriter("salary.txt"), true);

        ResourceBundle rb=ResourceBundle.getBundle("data", locale);
        pw.println(rb.getString("name_of_document"));
        pw.println(rb.getString("attributes"));
        pw.println("");

        for(int i =0;i<employees.length;i++){
            pw.println(employees[i].getName()+" "+salaries[i].toString());
        }
    }

    //метод запускает формирование зарплатной ведомости согласно файлу config.txt
    public void parseConfigAndPerform(){
        try (PrintWriter pw = new PrintWriter(new FileWriter("error.txt"), true)) {

        String[] string=new String[5];
        try (BufferedReader in = new BufferedReader(new FileReader("config.txt"))) {
            for(int i=0;i<string.length;i++){
                string[i]=in.readLine();
            }
        }
        catch(Exception e){
        }

        Employee[] employees;
        if(string[0].equalsIgnoreCase("testing")){
            employees=rep.allEmployeesOfCertainSection(Section.TESTING);
        }else if(string[0].equalsIgnoreCase("production")){
            employees=rep.allEmployeesOfCertainSection(Section.PRODUCTON);
        }else if (string[0].equalsIgnoreCase("marketing")) {
            employees=rep.allEmployeesOfCertainSection(Section.MARKETING);
        }else if(string[0].equalsIgnoreCase("all")){
            employees=rep.allEmployeesOfCompany();
        }else{
            //Error writing
            pw.println("Ошибка в определении отдела сотрудников(1 строка)");
            return;
        }

        Comparator howToSort;
        if (string[1].equalsIgnoreCase("surname")) {
            howToSort=new SurnameComparator();
        }else if(string[1].equalsIgnoreCase("hireday")){
            howToSort=new HireDayComparator();
        }else{
            //Error Writing
            pw.println("Ошибка в определении типа сортировки(2 строка)");
            return;
        }
        BonusType bonusType;
        if (string[2].equalsIgnoreCase("evenly")) {
            bonusType=BonusType.EVENLY;
        }else if(string[2].equalsIgnoreCase("proportionally")){
            bonusType=BonusType.PROPORTIONALLY;
        }else{
            //Error Writing
            pw.println("Ошибка в определении типа начисления премии(3 строка)");
            return;
        }

        BigDecimal salaryFund=BigDecimal.valueOf(Integer.parseInt(string[3]));

            Locale locale;
            if(string[4].equalsIgnoreCase("ru")){
                locale=new Locale("ru","RU");
            } else if (string[4].equalsIgnoreCase("en")) {
                locale=new Locale("en","EN");
            }else{
                pw.println("Ошибка в определении локали(4 строка)");
                return;
            }

        calculateSalary(employees,howToSort,bonusType,salaryFund,locale);
            System.out.println("Ok");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> allEmployeesOfMarketing(){
        ArrayList<Employee> list=new ArrayList<>(Arrays.asList(rep.allEmployeesOfCertainSection(Section.MARKETING)));
        return  list;
    }

    public ArrayList<Employee> allEmployeesOfProduction(){
        ArrayList<Employee> list=new ArrayList<>(Arrays.asList(rep.allEmployeesOfCertainSection(Section.PRODUCTON)));
        return  list;
    }

    public ArrayList<Employee> allEmployeesOfTesting(){
        ArrayList<Employee> list=new ArrayList<>(Arrays.asList(rep.allEmployeesOfCertainSection(Section.TESTING)));
        return  list;
    }







}

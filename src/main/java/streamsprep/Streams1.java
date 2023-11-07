package streamsprep;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Streams1 {

static String pattern = "==============================================================================================";
    public static void main(String[] args) {
        List<Emp> empList = buildEmpData();
        //1.
        // Find out the count of male and female employees present in the organization?
        findEmpCount(empList);

        //2.
        //Print the names of all departments in the organization
        findAllDepartments(empList);

        //3
        //Find Average age of male and female employees
        calculateAverageAgeOfEmp(empList);

        //4.
        //Get the Names of Employees who joined after 2015
        getEmpsByYearOfJoin(empList);

        //5.
        //Count the employees in the department
        countEmpsByDepartment(empList);

        //6.
        //Find out the average salary of each department
        averageSalaryByDepartment(empList);

        //7.
        //Find out the oldest employee, his/her age and department?
        getOldestEmployee(empList);

        //8.
        //Find average salary and total salary of the organization.
        averageSalaryOfOrg(empList);

        salaryDetailsForDepartment(empList);
    }

    private static void salaryDetailsForDepartment(List<Emp> empList) {
        System.out.println(pattern);
        System.out.println("Average salary details for each department =>");
        Map<String,DoubleSummaryStatistics> collect =
                empList.stream()
                        .collect(Collectors.groupingBy(Emp::getDepartment,
                                Collectors.summarizingDouble(Emp::getSalary)));
        Set<Map.Entry<String, DoubleSummaryStatistics>> entrySet = collect.entrySet();

        for(Map.Entry entry: entrySet){
            System.out.println("Department: "+ entry.getKey()+"\n");
            var value = (DoubleSummaryStatistics) entry.getValue();
            System.out.println("Average salary => "+value.getAverage());
            System.out.println("Total salary =>"+value.getSum()+"\n");

        }
        System.out.println(pattern);
    }

    private static void averageSalaryOfOrg(List<Emp> empList) {
        System.out.println(pattern);
        System.out.println("Average Salary details of organisation =>");
        DoubleSummaryStatistics empSalary = empList.stream()
                .collect(Collectors.summarizingDouble(Emp::getSalary));
        System.out.println("Average Salary => "+empSalary.getAverage());
        System.out.println("Total Salary =>"+empSalary.getSum());
        System.out.println(pattern);
    }

    private static void getOldestEmployee(List<Emp> empList) {
        System.out.println(pattern);
        //max() takes comparator as we are comparing age which of type int, so we are using comparingInt()
        Optional<Emp> emp = empList.stream()
                .max(Comparator.comparingInt(Emp::getAge));
        Emp emp1 = emp.orElseThrow(() -> new RuntimeException("No value found!!"));
        System.out.println(" Name : "+emp1.getName()+"\n Age : "+ emp1.getAge()+"\n Department : "+emp1.getDepartment());
        System.out.println(pattern);
    }

    private static void averageSalaryByDepartment(List<Emp> empList) {
        System.out.println(pattern);
        System.out.println("Print the average salary by Department");
        Map<String, Double> map =
        empList.stream().collect(Collectors.groupingBy(
                Emp::getDepartment, Collectors.averagingDouble(Emp::getSalary)
        ));
        Set<Map.Entry<String, Double>> entrySet = map.entrySet();
        for(Map.Entry entry: entrySet){
            System.out.println(entry.getKey()+ ": "+entry.getValue());
        }
        System.out.println(pattern);
    }

    private static void countEmpsByDepartment(List<Emp> empList) {
        System.out.println(pattern);
        System.out.println("Count the employees in the department!!\n");
        Map<String, Long> map = empList.stream()
                .collect(Collectors.groupingBy(
                        Emp::getDepartment, Collectors.counting()
                ));
        Set<Map.Entry<String, Long>> entrySet = map.entrySet();
        //entrySet.stream().map(entry -> entry.getKey() + ": " + entry.getValue()).forEach(System.out::println);
        for(Map.Entry entry : entrySet){
            System.out.println(entry.getKey() + ": "+entry.getValue());
        }
        System.out.println(pattern);
    }

    private static void getEmpsByYearOfJoin(List<Emp> empList) {
        System.out.println(pattern);
        System.out.println("Employees who joined after 2015 -> ");
        empList.stream().filter(n -> n.yearOfJoining > 2015)
                .map(Emp::getName)
                .sorted()
                .forEach(System.out :: println);
        System.out.println(pattern);
    }

    private static void calculateAverageAgeOfEmp(List<Emp> empList) {
        System.out.println(pattern);
        Map<String, Double> averageAge = empList.stream().collect(Collectors.groupingBy(Emp::getGender,
                                                                                        Collectors.averagingInt(
                                                               Emp::getAge)));
        System.out.println("Average age of employees by gender =>"+averageAge);
        System.out.println(pattern);
    }

    private static void findAllDepartments(List<Emp> empList) {
        //first map only department from Emp
        //then perform distinct on map data
        //finally print the values
        System.out.println(pattern);
        System.out.println("List of departments :: ");
        empList.stream().map(Emp::getDepartment).distinct().forEach(System.out::println);
        System.out.println(pattern);
    }

    private static void findEmpCount(List<Emp> empList) {
        System.out.println(pattern);
        //this is straight forward approach
        long maleEmpCount = empList.stream().filter(n -> n.gender.equals("Male")).count();
        System.out.println("number of employees =>"+ maleEmpCount);

        //if we want count all the gender
        //use grouping strategy
        //Note groupping will return the map
        // Key -> Emp:: getGender( which is String)
        //Value -> Collectors.counting() (which of type long)
        Map<String, Long> empCount = empList.stream()
                .collect(Collectors.groupingBy(
                        Emp::getGender, Collectors.counting()
                ));
        System.out.println("Employee count :: "+empCount);
        System.out.println(pattern);
    }

    private static List<Emp> buildEmpData() {
        List<Emp> employeeList = new ArrayList<>();

        employeeList.add(new Emp(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Emp(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Emp(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Emp(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Emp(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Emp(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Emp(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Emp(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Emp(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Emp(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Emp(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Emp(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Emp(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Emp(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Emp(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Emp(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Emp(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
        return employeeList;
    }
}

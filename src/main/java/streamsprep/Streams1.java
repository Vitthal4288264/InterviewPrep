package streamsprep;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Streams1 {


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
        Map<String, Double> averageAge = empList.stream().collect(Collectors.groupingBy(Emp::getGender,
                                                       Collectors.averagingInt(
                                                               Emp::getAge)));
        System.out.println("Average age of employees by gender =>"+averageAge);
    }

    private static void findAllDepartments(List<Emp> empList) {
        //first map only department from Emp
        //then perform distinct on map data
        //finally print the values
        System.out.println("List of departments :: ");
        empList.stream().map(Emp::getDepartment).distinct().forEach(System.out::println);
    }

    private static void findEmpCount(List<Emp> empList) {
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

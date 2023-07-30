package in.surya.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Employee {
	int id;
	String name;
	int age;
	String gender;
	String department;
	int yearOfJoining;
	double salary;

	public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.department = department;
		this.yearOfJoining = yearOfJoining;
		this.salary = salary;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getYearOfJoining() {
		return yearOfJoining;
	}

	public void setYearOfJoining(int yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", department="
				+ department + ", yearOfJoining=" + yearOfJoining + ", salary=" + salary + "]";
	}

}

public class Test {
	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<Employee>();

		employeeList.add(new Employee(1, "Jhansi", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(2, "Smith", 25, "Male", "Sales", 2015, 13500.0));
		employeeList.add(new Employee(3, "David", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(4, "Orlen", 28, "Male", "Development", 2014, 32500.0));
		employeeList.add(new Employee(5, "Charles", 27, "Male", "HR", 2013, 22700.0));
		employeeList.add(new Employee(6, "Cathy", 43, "Male", "Security", 2016, 10500.0));
		employeeList.add(new Employee(7, "Ramesh", 35, "Male", "Finance", 2010, 27000.0));
		employeeList.add(new Employee(8, "Suresh", 31, "Male", "Development", 2015, 34500.0));
		employeeList.add(new Employee(9, "Gita", 24, "Female", "Sales", 2016, 11500.0));
		employeeList.add(new Employee(10, "Mahesh", 38, "Male", "Security", 2015, 11000.5));
		employeeList.add(new Employee(11, "Gouri", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(12, "Nithin", 25, "Male", "Development", 2016, 28200.0));
		employeeList.add(new Employee(13, "Swathi", 27, "Female", "Finance", 2013, 21300.0));
		employeeList.add(new Employee(14, "Buttler", 24, "Male", "Sales", 2017, 10700.5));
		employeeList.add(new Employee(15, "Ashok", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(16, "Sanvi", 26, "Female", "Development", 2015, 28900.0));

		// 1. How many male and female employees are there in the organization ?

		long femaleCount = employeeList.stream().filter(emp -> emp.gender.equals("Female")).count();
		System.out.println("total no:of femailes =" + femaleCount);

		long Malecount = employeeList.stream().filter(emp -> emp.gender.equals("Male")).count();
		System.out.println("total no:of males =" + Malecount);
		System.out.println("==================================================================");
		// 2. Print the name of all departments in the organization ?

		Stream<Employee> stream = employeeList.stream();
		Stream<String> map = stream.map(emp -> emp.department);
		Stream<String> distinct = map.distinct();
		distinct.forEach(i -> System.out.println(i));
		System.out.println("==================================================================");
		// 3 What is the average age of male and female employees ?
		Stream<Employee> stream1 = employeeList.stream();
		Double avgAge = stream1.collect(Collectors.averagingInt(emp -> emp.age));
		System.out.println(avgAge.intValue());
		System.out.println("==================================================================");
		// Get the details of highest paid employee in the organization ?
		Stream<Employee> stream2 = employeeList.stream();
		Optional<Employee> employeeWithMaxSalary = stream2
				.collect(Collectors.maxBy(Comparator.comparingDouble(emp -> emp.salary)));

		// 4 Check if the result is present and get the employee with the maximum salary
		if (employeeWithMaxSalary.isPresent()) {
			System.out.println(employeeWithMaxSalary);
		} else {
			System.out.println("No employees in the list.");
		}
		System.out.println("==================================================================");
		// 5 Get the names of all employees who have joined after 2015 ?
		Stream<Employee> stream3 = employeeList.stream();
		Stream<Employee> filter = stream3.filter(emp -> emp.yearOfJoining > 2015);
		filter.forEach(i -> System.out.println(i.name));
		System.out.println("==================================================================");
		// 6 Count the number of employees in each department ?
		Stream<Employee> stream4 = employeeList.stream();
		Map<String, Long> employes = stream4
				.collect(Collectors.groupingBy(emp -> emp.getDepartment(), Collectors.counting()));
		System.out.println(employes);
		System.out.println("==================================================================");
		// 7What is the average salary of each department ?
		Stream<Employee> stream5 = employeeList.stream();
		Map<String, Double> avgSalaryDept = stream5.collect(
				Collectors.groupingBy(emp -> emp.getDepartment(), Collectors.averagingDouble(emp -> emp.salary)));
		System.out.println(avgSalaryDept);
		System.out.println("==================================================================");
		// 8 Get the details of youngest male employee in the Development department ?
		Stream<Employee> stream6 = employeeList.stream();
		Map<String, Optional<Employee>> youngestmale = stream6.filter(emp -> emp.gender.equals("Male")).collect(
				Collectors.groupingBy(emp -> emp.department, Collectors.minBy(Comparator.comparing(emp -> emp.age))));
		System.out.println(youngestmale);
		System.out.println("==================================================================");
		// 9 Who has the most working experience in the organization ?

		Stream<Employee> stream7 = employeeList.stream();
		Optional<Employee> empp = stream7.collect(Collectors.minBy(Comparator.comparing(emp -> emp.yearOfJoining)));
		System.out.println(empp.get());
		System.out.println("==================================================================");
		// 10 How many male and female employees are there in the Sales team ?

		Stream<Employee> stream8 = employeeList.stream();
		Map<String, Long> sales = stream8.filter(emp -> emp.department.equals("Sales"))
				.collect(Collectors.groupingBy(emp -> emp.gender, Collectors.counting()));
		System.out.println(sales);

		System.out.println("==================================================================");
		// 11 What is the average salary of male and female employees ?
		Stream<Employee> stream9 = employeeList.stream();
		Map<String, Double> avg = stream9
				.collect(Collectors.groupingBy(emp -> emp.gender, Collectors.averagingDouble(emp -> emp.salary)));
		System.out.println(avg);
		System.out.println("==================================================================");
		// 12 List down the names of all employees in each department ?
		Stream<Employee> stream10 = employeeList.stream();
		Map<String, List<String>> namesByDepartment = stream10.collect(
				Collectors.groupingBy(emp -> emp.department, Collectors.mapping(emp -> emp.name, Collectors.toList())));

		namesByDepartment.forEach((Departement, names) -> {
			System.out.println(Departement + " " + names);
		});
		
		System.out.println("==================================================================");
		// 13 What is the average salary and total salary of the whole organization ?  
		Stream<Employee> stream11 = employeeList.stream();

		// Calculate the total salary
		double totalSalary = stream11.collect(Collectors.summingDouble(emp -> emp.getSalary()));

		// Calculate the average salary
		Stream<Employee> stream12 = employeeList.stream(); // Reset the stream as it was consumed in the previous operation
		double averageSalary = stream12.collect(Collectors.averagingDouble(emp -> emp.getSalary()));

		System.out.println("Total Salary: " + totalSalary);
		System.out.println("Average Salary: " + averageSalary);

		System.out.println("==================================================================");
		// 14Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years ?
		Stream<Employee> stream13 = employeeList.stream();
		stream13.filter(emp->emp.age>=25).toList().forEach(i->System.out.println(i));
		
		

	}
}

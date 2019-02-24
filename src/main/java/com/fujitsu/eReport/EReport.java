package main.java.com.fujitsu.eReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import main.java.com.fujitsu.eReport.dto.Employee;

public interface EReport {
	/**
	Entry Method to start the program
	*/
	public static void main(String[] args) throws IOException {
		String[] lines = Files.readAllLines(new File("employees.dat").toPath()).toArray(new String[0]);

		List<String> list = new ArrayList<>();
		List<Employee> emplists = new ArrayList<>();
		Employee employee;

		for (String line : lines) {
			if (!line.startsWith("#")) {
				list.add(line);
				String[] data_array = line.split(",");
				String[] fullname_array = data_array[1].trim().split("\\s+");
				employee = new Employee(Integer.parseInt(data_array[0]), fullname_array[0], fullname_array[1]);
				emplists.add(employee);
			}
		}
		
		sortByEmployeeId(emplists);
		sortByEmployeeLastName(emplists);
		
		sortByEmployeeId_Using_Java8(emplists);
		sortByEmployeeLast_Name_Using_java8(emplists);

		sortByEmployeeIdUsing_TreeMap(list);
		sortByEmployeeLastName_UsingTreeMap(list);
	}

	/**
	This method does sort employee by employee id
	@param First Parameter indicate EmployeeList
	*/
	public static void sortByEmployeeId(List<Employee> emplists) {
		Collections.sort(emplists, new Comparator<Employee>() {
			@Override
			public int compare(Employee emp1, Employee emp2) {
				return ((Integer) emp1.getEmpId()).compareTo((Integer) emp2.getEmpId());
			}
		});
		printListOfEmployee(emplists,"Processing by employee number...");
	}

	/**
	This method does sort employee by their LastName
	@param First Parameter indicate EmployeeList
	*/
	public static void sortByEmployeeLastName(List<Employee> emplists) {
		Collections.sort(emplists, new Comparator<Employee>() {
			@Override
			public int compare(Employee emp1, Employee emp2) {
				return emp1.getLastName().compareTo(emp2.getLastName());
			}
		});
		printListOfEmployee(emplists,"Processing by last (family) Name...");
	}
	
	/**
	This method does sort employee by employee id
	@param First Parameter indicate EmployeeList
	*/
	public static void sortByEmployeeId_Using_Java8(List<Employee> emplists) {
		List<Employee> emplistResult = emplists.stream().sorted(Comparator.comparing(Employee::getEmpId)).collect(Collectors.toList());
		printListOfEmployee(emplistResult,"Processing by employee number Using Java 8...");
	}

	/**
	This method does sort employee by their LastName
	@param First Parameter indicate EmployeeList
	*/
	public static void sortByEmployeeLast_Name_Using_java8(List<Employee> emplists) {
		List<Employee> emplistResult = emplists.stream().sorted(Comparator.comparing(Employee::getLastName)).collect(Collectors.toList());
		printListOfEmployee(emplistResult,"Processing by last (family) Name Using Java 8...");
	}

	/**
	This method does sort employee by employee id
	@param First Parameter indicate EmployeeList
	*/
	public static void sortByEmployeeIdUsing_TreeMap(List<String> list) {
		Map<Integer, String> map = new TreeMap<Integer, String>();
		for (String line : list) {
			int employeeNumber = Integer.parseInt(line.substring(0, 3));
			map.put(employeeNumber, line.substring(4));
		}
		System.out.println("Processing by employee number Using Tree Map...");
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue().trim());
		}
		System.out.println("\n");
	}
	
	/**
	This method does sort employee by their LastName
	@param First Parameter indicate EmployeeList
	*/
	public static void sortByEmployeeLastName_UsingTreeMap(List<String> list) {
		TreeMap<String, String> hmap = new TreeMap<>();
		for (String line : list) {
			String[] elements = line.split(",");
			String[] fullname = elements[1].trim().split("\\s+");
			hmap.put(fullname[1], elements[0] + "," + elements[1].trim());
		}
		System.out.println("Processing by last (family) Name Using Tree Map...");
		for (Map.Entry<String, String> entry : hmap.entrySet()) {
			System.out.println(entry.getValue().trim());
		}

	}
	
	/**
	This method does print all employee information in console
	@param First Parameter indicate EmployeeList
	@param Second Parameter indicate info.
	*/
	public static void printListOfEmployee(List<Employee> emplists,String info)
	{
		System.out.println(info);
		for (Employee employee : emplists) {
			System.out.println(employee.getEmpId() + "," + employee.getFirstName() + " " + employee.getLastName());
		}
		System.out.println("\n");
	}
}

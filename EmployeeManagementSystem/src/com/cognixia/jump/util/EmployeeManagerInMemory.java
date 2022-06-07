package com.cognixia.jump.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cognixia.jump.exceptions.EmployeeNotFoundException;
import com.cognixia.jump.model.Employee;

public class EmployeeManagerInMemory implements EmployeeManager {
	
	private static int idCounter = 1;
	private static List<Employee> employeeList = new ArrayList<Employee>();
	
	static {
		employeeList.add(new Employee(idCounter++, "Tom", "HR", 50000, "tom@email.com"));
		employeeList.add(new Employee(idCounter++, "Mary", "HR", 50000, "mary@email.com"));
		employeeList.add(new Employee(idCounter++, "Anna", "IT", 50000, "anna@email.com"));
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeList;
	}

	@Override
	public Employee findEmployeeById(int id) throws EmployeeNotFoundException {
		
		for(Employee e : employeeList) {
			if(e.getId() == id) {
				return e;
			}
		}
		
		throw new EmployeeNotFoundException(id);
	}

	@Override
	public boolean createEmployee(Employee empl) {
		
		// reset id to be unique using the counter
		empl.setId(idCounter++);
		
		employeeList.add(empl);
		
		return false;
	}

	@Override
	public boolean deleteEmployee(int id) {
		// TODO Auto-generated method stub
//		for(int i = 0; i < employeeList.size(); i++) {
//			if(employeeList.get(i).getId() == id) {
//				employeeList.remove(i);
//				break;
//			}
//		}
		
		Employee delEmployee = employeeList.stream()
				.filter( e -> e.getId() == id)
				.findFirst()
				.get();
		employeeList.remove(delEmployee);
		
		return false;
	}

	@Override
	public boolean updateEmployee(Employee empl) {
		// TODO Auto-generated method stub
		
//		employeeList.get(empl).setName(empl.getName());
		if(!empl.getName().contains(employeeList.get(empl.getId()).getName()))
		{
			System.out.println("\nEmployee's name changed to " + empl.getName());
			employeeList.get(empl.getId()).setName(empl.getName());
			return false;
			
		}
		
		if(!empl.getDepartment().contains(employeeList.get(empl.getId()).getDepartment())) 
		{
			System.out.println("\nEmployee's department changed to " + empl.getDepartment());
			employeeList.get(empl.getId()).setDepartment(empl.getDepartment());
			return false;
		}
		
		if(empl.getSalary() != (employeeList.get(empl.getId()).getSalary()))
		{
			System.out.println("\nEmployee's salary changed to " + empl.getSalary());
			employeeList.get(empl.getId()).setSalary(empl.getSalary());
			return false;
		}
		
		if(!empl.getEmail().contains(employeeList.get(empl.getId()).getEmail())) 
		{
			System.out.println("\nEmployee's email changed to " + empl.getEmail());
			employeeList.get(empl.getId()).setEmail(empl.getEmail());
			return false;
		}
		
		
		
		return false;
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String dept) {
		// TODO Auto-generated method stub
		List<Employee> department = employeeList.stream()
				.filter(e -> e.getDepartment().equals(dept))
				.collect( Collectors.toList());
		
		return department;
	}

}

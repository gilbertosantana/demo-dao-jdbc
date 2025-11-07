package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("===== TEST 1: department findById =====");
		Department department = departmentDao.findById(4);
		System.out.println(department);
		
		System.out.println("===== TEST 2: department findAll =====");
		List<Department> departments = departmentDao.findAll();
		departments.forEach(System.out::println);

		System.out.println("===== TEST 3: department insert =====");
		Department newDepartment = new Department(null, "Geek");
		departmentDao.insert(newDepartment);
		System.out.println("New Department: " + newDepartment.getId());
		
		System.out.println("\n===== TEST 4: department update =====");
		department = departmentDao.findById(6);
		department.setName("Cozinha");
		departmentDao.update(department);
		System.out.println("Update department completed");
		
		sc.close();
		
	}

}

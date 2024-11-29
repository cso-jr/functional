package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) throws ParseException {
	
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
				
		
		
		System.out.print("Enter full file path: ");
		String path = sc.next();
		
		//String path = "C://temp//in.txt";
		
		List<Employee> listEmp = new ArrayList<>();
		
		// try with resources para ler o arquivo
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			
			while (line != null) {	
				String[] fields = line.split(","); 
				String name = fields[0];
				String email = fields[1];
				double salary = Double.parseDouble(fields[2]);
				
				listEmp.add(new Employee(name, email, salary));
				
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salaryLimit = sc.nextDouble();
								
			List<String> sEmail = listEmp.stream()
					.filter(e -> e.getSalary() > salaryLimit)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			
		System.out.println("Email of people whose salary is more than " + salaryLimit + ": ");
			
		sEmail.forEach(System.out::println);

		Double sumSalary = listEmp.stream()
				.filter(e -> e.getName().startsWith("M"))
				.map(e -> e.getSalary())
				.reduce(0.0, (x,y) -> x + y);
		
		System.out.println("Sum of salary of people whose name starts with 'M': " + sumSalary);
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			
		} catch (IOException e1) {
			System.out.println(e1.getStackTrace());
		}
			
		
		sc.close();
	}

}

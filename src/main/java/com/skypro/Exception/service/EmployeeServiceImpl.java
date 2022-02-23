package com.skypro.Exception.service;

import com.skypro.Exception.exception.AddingAnExistingEmployeeException;
import com.skypro.Exception.exception.EmployeeNotFoundException;
import com.skypro.Exception.exception.TheArrayEmployeesIsFullException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Employee[] employees = new Employee[10];


    @Override
    public Employee add(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        for (Employee employee : employees) {
            if (employee != null && employee.equals(newEmployee)) {
                throw new AddingAnExistingEmployeeException();
            }
        }

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = newEmployee;
                return employees[i];
            }
        }
        throw new TheArrayEmployeesIsFullException();
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].equals(newEmployee)) {
                Employee e = employees[i];
                employees[i] = null;
                return e;
            }
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        for (Employee employee : employees) {
            if (employee != null && employee.equals(newEmployee)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }
}

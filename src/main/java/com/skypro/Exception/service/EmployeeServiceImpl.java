package com.skypro.Exception.service;

import com.skypro.Exception.exception.AddingAnExistingEmployee;
import com.skypro.Exception.exception.EmployeeNotFound;
import com.skypro.Exception.exception.TheArrayEmployeesIsFull;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Employee[] employees = new Employee[10];


    @Override
    public Employee add(String firstName, String lastName) {

        for (Employee employee : employees) {
            if (employee != null) {
                if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                    throw new AddingAnExistingEmployee();
                }
            }
        }

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = new Employee(firstName, lastName);
                return employees[i];
            }
        }
        throw new TheArrayEmployeesIsFull();
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getFirstName().equals(firstName) && employees[i].getLastName().equals(lastName)) {
                    Employee e = employees[i];
                    employees[i] = null;
                    return e;
                }
            }
        }
        throw new EmployeeNotFound();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee != null) {
                if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                    return employee;
                }
            }
        }
        throw new EmployeeNotFound();
    }
}

package com.skypro.Exception.service;

import com.skypro.Exception.exception.AddingAnExistingEmployeeException;
import com.skypro.Exception.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee add(String firstName, String lastName, int department, double salary) {
        if (!isAlpha(firstName) || !isAlpha(lastName)) {
            throw new AddingAnExistingEmployeeException();
        }
        Employee newEmployee = new Employee(capitalize(firstName),
                capitalize(lastName), department, salary);

        if (employees.containsKey(newEmployee.getFullName())) {
            throw new AddingAnExistingEmployeeException();
        }

        employees.put(newEmployee.getFullName(), newEmployee);
        return newEmployee;
    }

    @Override
    public Employee remove(String fullName) {
        if (!isAlphaSpace(fullName)) {
            throw new AddingAnExistingEmployeeException();
        }

        String fullNameCap = capitalize(split(fullName)[0]) + " " + capitalize(split(fullName)[1]);

        Employee employee = employees.remove(fullNameCap);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        return employee;
    }

    @Override
    public Employee find(String fullName) {
        if (!isAlphaSpace(fullName)) {
            throw new AddingAnExistingEmployeeException();
        }

        String fullNameCap = capitalize(split(fullName)[0]) + " " + capitalize(split(fullName)[1]);

        Employee employee = employees.get(fullNameCap);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        return employee;
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

}

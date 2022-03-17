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
    public List<Employee> getAll() {
        return employees.values().stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }

    //Второй вариант
    @Override
    public Map<Integer, List<Employee>> getAllGroupedByDepartment() {
        return employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int department) {
        return getEmployeeStreamByDepartment(department)
                .collect(Collectors.toList());
    }

    @Override
    public Employee getMinSalaryEmployee(int department) {
        return getEmployeeStreamByDepartment(department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee getMaxSalaryEmployee(int department) {
        return getEmployeeStreamByDepartment(department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    private Stream<Employee> getEmployeeStreamByDepartment(int department) {
        return employees.values().stream()
                .filter(employee -> employee.getDepartment() == department);
    }

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
}

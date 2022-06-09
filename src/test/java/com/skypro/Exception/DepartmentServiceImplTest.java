package com.skypro.Exception;

import com.skypro.Exception.exception.EmployeeNotFoundException;
import com.skypro.Exception.service.DepartmentServiceImpl;
import com.skypro.Exception.service.Employee;
import com.skypro.Exception.service.EmployeeService;
import com.skypro.Exception.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    private final EmployeeServiceImpl employeeServiceMock = mock(EmployeeServiceImpl.class);

    private final DepartmentServiceImpl out = new DepartmentServiceImpl(employeeServiceMock);
    Map<String, Employee> employeeMap = new HashMap<>();

    @BeforeEach
    public void adding() {
        Employee employee1 = new Employee("Иван", "Иванов", 1, 11111);
        Employee employee2 = new Employee("Олег", "Иванов", 1, 22222);
        Employee employee3 = new Employee("Игорь", "Иванов", 2, 33333);
        Employee employee4 = new Employee("Антон", "Иванов", 2, 44444);
        Employee employee5 = new Employee("Даниил", "Иванов", 3, 55555);
        Employee employee6 = new Employee("Илья", "Иванов", 3, 66666);
        employeeMap.put(employee1.getFullName(), employee1);
        employeeMap.put(employee2.getFullName(), employee2);
        employeeMap.put(employee3.getFullName(), employee3);
        employeeMap.put(employee4.getFullName(), employee4);
        employeeMap.put(employee5.getFullName(), employee5);
        employeeMap.put(employee6.getFullName(), employee6);
    }

    @Test
    public void returnAnEmployeeWithMaxSalary() {
        when(employeeServiceMock.getEmployees()).thenReturn(employeeMap);

        Assertions.assertEquals(out.getMaxSalaryEmployee(2), employeeMap.get("Иванов Антон"));

        Assertions.assertEquals(out.getMaxSalaryEmployee(2).getDepartment(), 2);
    }

    @Test
    public void returnAnEmployeeWithMinSalary() {
        when(employeeServiceMock.getEmployees()).thenReturn(employeeMap);

        Assertions.assertEquals(out.getMinSalaryEmployee(3), employeeMap.get("Иванов Даниил"));

        Assertions.assertEquals(out.getMinSalaryEmployee(3).getDepartment(), 3);
    }

    @Test
    public void shouldGenerateAnExceptionIfThereIsNoSuchDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(employeeMap);

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.getMaxSalaryEmployee(5));

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.getMinSalaryEmployee(5));
    }

    @Test
    public void shouldReturnListOfDepartmentEmployees() {
        when(employeeServiceMock.getEmployees()).thenReturn(employeeMap);

        List<Employee> employees = List.of(new Employee("Иван", "Иванов", 1, 11111),
                new Employee("Олег", "Иванов", 1, 22222));

        Assertions.assertEquals(employees, out.getEmployeesByDepartment(1));
    }

    @Test
    public void shouldReturnListOfEmployeesSortedByDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(employeeMap);

        List<Employee> employees = new ArrayList<>(employeeMap.values());

        employees.sort(Comparator.comparing(Employee::getDepartment));

        Assertions.assertEquals(employees, out.getAll());
    }
}
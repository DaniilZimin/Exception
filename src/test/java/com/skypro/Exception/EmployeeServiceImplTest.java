package com.skypro.Exception;

import com.skypro.Exception.exception.AddingAnExistingEmployeeException;
import com.skypro.Exception.exception.EmployeeNotFoundException;
import com.skypro.Exception.service.Employee;
import com.skypro.Exception.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EmployeeServiceImplTest {

    private final EmployeeServiceImpl out = new EmployeeServiceImpl();

    @Test
    public void mustReturnTheEmployeeWhoWasAdded() {
        Employee employee = new Employee("Иван", "Иванов", 1, 11111);
        Assertions.assertEquals(employee, out.add("Иван", "Иванов", 1, 11111));
    }

    @Test
    public void shouldCapitalizesFirstLetters() {
        Employee employee = out.add("иван", "иванов", 1, 11111);
        String firstName = "Иван";
        String lastName = "Иванов";
        Assertions.assertEquals(firstName, employee.getFirstName());
        Assertions.assertEquals(lastName, employee.getLastName());
    }

    @Test
    public void shouldThrowAddingAnExistingEmployeeExceptionIfTheStringContainsAnythingOtherThanLetters() {
        Assertions.assertThrows(AddingAnExistingEmployeeException.class, () -> out.add("Иван1", "Иванов", 1, 11111));
    }

    @Test
    public void shouldThrowAddingAnExistingEmployeeExceptionIfSuchEmployeeAlreadyExists() {
        out.add("Иван", "Иванов", 1, 11111);
        Assertions.assertThrows(AddingAnExistingEmployeeException.class, () -> out.add("Иван", "Иванов", 1, 11111));
    }

    @Test
    public void mustReturnTheEmployeeWhoWasRemoved() {
        Employee employee = out.add("Иван", "Иванов", 1, 11111);
        Assertions.assertEquals(employee, out.remove("Иванов Иван"));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionIfThereIsNoSuchEmployee() {
        Employee employee = out.add("Иван", "Иванов", 1, 11111);
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.remove("Сергеев Сергей"));
    }

    @Test
    public void shouldThrowAddingAnExistingEmployeeExceptionIfTheInputParameterContainsMoreThanJustLettersAndSpace() {
        Assertions.assertThrows(AddingAnExistingEmployeeException.class, () -> out.remove("Иванов1 1Иван"));
    }

    @Test
    public void mustReturnTheEmployeeByFullName() {
        Employee employee = out.add("Иван", "Иванов", 1, 11111);
        Assertions.assertEquals(employee, out.find("Иванов Иван"));
    }

    @Test
    public void shouldThrowAddingAnExistingEmployeeExceptionIfTheInputParameterContainsMoreThanJustLettersAndSpaceFind() {
        Assertions.assertThrows(AddingAnExistingEmployeeException.class, () -> out.find("Иванов1 1Иван"));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionIfThereIsNoSuchEmployeeFind() {
        Employee employee = out.add("Иван", "Иванов", 1, 11111);
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.find("Сергеев Сергей"));
    }
}
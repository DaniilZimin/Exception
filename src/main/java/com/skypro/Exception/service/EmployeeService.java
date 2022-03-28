package com.skypro.Exception.service;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    /**
     * @param firstName  имя
     * @param lastName   фамилия
     * @param department отдел
     * @param salary     зарплата
     * @return добавленный сотрудник
     */
    Employee add(String firstName, String lastName, int department, double salary);

    /**
     * @param fullName ФИО
     * @return удаленный сотрудник
     */
    Employee remove(String fullName);

    /**
     * @param fullName ФИО
     * @return найденный сотрудник
     */
    Employee find(String fullName);

    Map<String, Employee> getEmployees();
}

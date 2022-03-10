package com.skypro.Exception.service;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    /**
     * @return список сотрудников отсортированный по департаментам
     */
    List<Employee> getAll();

    /**
     * @return сгруппированный список по департаментам
     */
    Map<Integer, List<Employee>> getAllGroupedByDepartment();

    /**
     * @param department отдел
     * @return список сотрудников отдела
     */
    List<Employee> getEmployeesByDepartment(int department);

    /**
     * @param department отдел
     * @return сотрудник с минимальной зарплатой в отделе
     */
    Employee getMinSalaryEmployee(int department);

    /**
     * @param department отдел
     * @return сотрудник с максимальной зарплатой в отделе
     */
    Employee getMaxSalaryEmployee(int department);

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
}

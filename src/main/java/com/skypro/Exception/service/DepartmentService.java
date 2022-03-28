package com.skypro.Exception.service;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
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
    //     */
    Employee getMaxSalaryEmployee(int department);
}

package com.skypro.Exception.service;

public interface EmployeeService {
    Employee add (String firstName, String lastName);
    Employee remove (String firstName, String lastName);
    Employee find (String firstName, String lastName);
}

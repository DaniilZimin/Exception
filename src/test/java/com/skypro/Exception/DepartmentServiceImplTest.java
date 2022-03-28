package com.skypro.Exception;

import com.skypro.Exception.service.DepartmentServiceImpl;
import com.skypro.Exception.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    private final EmployeeServiceImpl employeeServiceMock = mock(EmployeeServiceImpl.class);
    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    public void initOut(){
        out = new DepartmentServiceImpl(employeeServiceMock);
    }






}





package example.open.api.service.impl;

import javax.inject.Singleton;

import example.open.api.dto.EmployeeDTO;
import example.open.api.service.EmployeeService;

@Singleton
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public EmployeeDTO getEmployee(String id) {
        EmployeeDTO employee = new EmployeeDTO(id,"a",1);
        return employee;
    }

}
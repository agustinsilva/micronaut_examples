package example.open.api.service;

import example.open.api.dto.EmployeeDTO;

public interface EmployeeService {

    public EmployeeDTO getEmployee(String id);
}
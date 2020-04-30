package example.open.api.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.validation.constraints.NotBlank;

import example.open.api.dto.EmployeeDTO;
import example.open.api.service.EmployeeService;

@Controller("/")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Get(uri = "/employees/{id}")
    public HttpResponse<EmployeeDTO> getEmployee(@NotBlank String id){
        return HttpResponse.ok(employeeService.getEmployee(id));
    }
}
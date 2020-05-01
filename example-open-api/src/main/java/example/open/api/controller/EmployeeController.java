package example.open.api.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.validation.constraints.NotBlank;

import example.open.api.dto.EmployeeDTO;
import example.open.api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller("/")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Get(uri = "/employees/{id}")
    @Operation(summary = "Get an employee detail.",
            description = "Get an employee detail by id."
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(name="employee"))
    )
    @ApiResponse(responseCode = "200", description = "Employee exist!")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @Tag(name = "employees")
    public HttpResponse<EmployeeDTO> getEmployee(@Parameter(description="The id of the employee") @NotBlank String id){
        return HttpResponse.ok(employeeService.getEmployee(id));
    }
}
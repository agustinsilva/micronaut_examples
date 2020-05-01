package example.open.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name="employee", description="Employee detailed information.")
public class EmployeeDTO {
    private String id;
    private String name;
    private int age;

}
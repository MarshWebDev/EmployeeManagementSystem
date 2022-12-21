package management.employee.Application.dto;

import lombok.Getter;
import management.employee.Application.database.entity.Employee;

@Getter
public class AddEmployeeDto {
    private Employee employee;
    private Long departmentId;
}

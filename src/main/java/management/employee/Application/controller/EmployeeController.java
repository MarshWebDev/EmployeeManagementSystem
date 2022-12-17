package management.employee.Application.controller;

import lombok.AllArgsConstructor;
import management.employee.Application.database.entity.Employee;
import management.employee.Application.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static management.employee.Application.config.RequestMappingPath.EMPLOYEE_API_PATH;

@RestController
@RequestMapping(path = EMPLOYEE_API_PATH)
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path = "/employees")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }
}
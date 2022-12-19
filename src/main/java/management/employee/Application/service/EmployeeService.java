package management.employee.Application.service;

import lombok.AllArgsConstructor;
import management.employee.Application.database.entity.Employee;
import management.employee.Application.exception.BadRequestException;
import management.employee.Application.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() ->
                new BadRequestException("Employee by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }
}

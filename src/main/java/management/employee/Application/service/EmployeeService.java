package management.employee.Application.service;

import lombok.AllArgsConstructor;
import management.employee.Application.database.entity.Employee;
import management.employee.Application.exception.BadRequestException;
import management.employee.Application.repo.EmployeeRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

//    public List<Employee> findAllEmployees() {
//        return employeeRepo.findAll();
//    }

    //TODO: Add sorting ability
    public Page<Employee> getEmployees(Integer page) {
        return employeeRepo.findAll(PageRequest.of(
                page,
                20,
                Sort.by("id").ascending()
        ));
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() ->
                new BadRequestException("Employee by id " + id + " was not found"));
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }
}

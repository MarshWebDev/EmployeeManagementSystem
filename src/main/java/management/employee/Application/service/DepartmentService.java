package management.employee.Application.service;

import lombok.AllArgsConstructor;
import management.employee.Application.database.entity.Department;
import management.employee.Application.database.entity.Employee;
import management.employee.Application.exception.BadRequestException;
import management.employee.Application.repo.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepo departmentRepo;

    public List<Department> findAllDepartments() {
        return departmentRepo.findAll();
    }

    public Department findDepartmentById(Long id) {
        return departmentRepo.findById(id).orElseThrow(() ->
                new BadRequestException("Department by id " + id + " was not found"));
    }
}

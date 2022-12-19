package management.employee.Application.config;

import lombok.AllArgsConstructor;
import management.employee.Application.database.entity.Department;
import management.employee.Application.database.entity.Employee;
import management.employee.Application.repo.DepartmentRepo;
import management.employee.Application.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@AllArgsConstructor
public class TestingConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            EmployeeRepo employeeRepo,
            DepartmentRepo departmentRepo
    ) {
        return args -> {
            departmentRepo.findById(4L).ifPresent(department ->
                    System.out.println(department.getName()));
        };
    }
}

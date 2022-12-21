package management.employee.Application.config;

import lombok.AllArgsConstructor;
import management.employee.Application.database.entity.Employee;
import management.employee.Application.repo.DepartmentRepo;
import management.employee.Application.repo.EmployeeRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;

@Configuration
@AllArgsConstructor
public class TestingConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            EmployeeRepo employeeRepo,
            DepartmentRepo departmentRepo
    ) {
        return args -> {

        };
    }

    public void generate50People(EmployeeRepo employeeRepo) {

    }
}

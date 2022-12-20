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
        Faker faker = new Faker();
        for (int i = 0; i <= 50; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);
            Employee employee = new Employee(
                    firstName,
                    lastName,
                    email,
                    faker.number().randomNumber(9, false),
                    faker.number().randomNumber(9, false),
                    LocalDateTime.now()
            );
            employeeRepo.save(employee);
        }
    }
}

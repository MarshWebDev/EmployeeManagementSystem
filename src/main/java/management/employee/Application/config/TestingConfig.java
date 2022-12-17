package management.employee.Application.config;

import lombok.AllArgsConstructor;
import management.employee.Application.database.entity.Employee;
import management.employee.Application.database.entity.EmployeeImage;
import management.employee.Application.database.entity.Permission;
import management.employee.Application.database.entity.Role;
import management.employee.Application.repo.EmployeeRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@AllArgsConstructor
public class TestingConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepo employeeRepo) {
        return args -> {
            Employee marshall = new Employee(
                    "Marshall",
                    "Nickolauson",
                    "marshall@gmail.com",
                    1234567890L,
                    9876543210L,
                    LocalDateTime.now(),
                    true,
                    false
            );

            EmployeeImage employeeImage = new EmployeeImage(
                    marshall,
                    "/images/employee/profile/test.png"
            );

            Role adminRole = new Role("ADMIN");
            adminRole.addPermission(new Permission("admin:read"));
            adminRole.addPermission(new Permission("admin:write"));

            marshall.setEmployeeImage(employeeImage);
            marshall.addRole(adminRole);

            employeeRepo.save(marshall);

            employeeRepo.findById(1L).ifPresentOrElse(employee1 -> {
                System.out.println("Name: " + employee1.getFirstName() + " " + employee1.getLastName());
            }, () -> System.out.println("Employee not found"));

            Employee jared = new Employee(
                    "Jared",
                    "Torell",
                    "jared@gmail.com",
                    1234567890L,
                    9876545327410L,
                    LocalDateTime.now(),
                    true,
                    false
            );

            Employee nancy = new Employee(
                    "Nancy",
                    "Torell",
                    "nancy@gmail.com",
                    1234567890L,
                    98234765432150L,
                    LocalDateTime.now(),
                    true,
                    false
            );

            Employee christian = new Employee(
                    "Christian",
                    "Lasley",
                    "christian@gmail.com",
                    1234567890L,
                    98726543234210L,
                    LocalDateTime.now(),
                    true,
                    false
            );

            Employee charo = new Employee(
                    "Charo",
                    "Paneque",
                    "charo@gmail.com",
                    1234567890L,
                    8765432150L,
                    LocalDateTime.now(),
                    true,
                    false
            );

            Employee clara = new Employee(
                    "Clara",
                    "Perry",
                    "clara@gmail.com",
                    1234567890L,
                    983210L,
                    LocalDateTime.now(),
                    true,
                    false
            );

            employeeRepo.saveAll(List.of(jared, nancy, charo, christian, clara));
        };
    }
}

package management.employee.Application;

import lombok.AllArgsConstructor;
import management.employee.Application.database.entity.*;
import management.employee.Application.repository.EmployeeRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner commandLineRunner(
			EmployeeRepo employeeRepo
	) {
		return args -> {
			Employee marshall = new Employee(
					"Marshall",
					"Nickolauson",
					"marshall@gmail.com",
					1234567890L,
					9876543210L,
					"marshall",
					passwordEncoder.encode("password"),
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
				System.out.println("Username: " + employee1.getUsername());
				System.out.println("Password: " + employee1.getPassword());
				System.out.println("Roles: " + employee1.getRoles());
				System.out.println("Permissions: " + employee1.getAuthorities());
			}, () -> System.out.println("Employee not found"));
		};
	}
}

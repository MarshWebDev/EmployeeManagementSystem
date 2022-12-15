package management.employee.Application.repository;

import management.employee.Application.database.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.username = ?1")
    Optional<Employee> findByUsername(String username);
}

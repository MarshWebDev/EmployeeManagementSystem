package management.employee.Application.repo;

import management.employee.Application.database.entity.Department;
import management.employee.Application.database.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Transactional
    @Modifying
    @Query("update Employee e set e.department = ?1 where e.id = ?2")
    void updateDepartment(Department department, Long employeeId);
}

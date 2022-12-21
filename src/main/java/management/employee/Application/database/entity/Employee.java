package management.employee.Application.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter @NoArgsConstructor
@Entity(name = "Employee")
@Table(
        name = "employee",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique_constraint",
                columnNames = "email"
        )
)
public class Employee {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private Long phone;
    @Column(unique = true)
    private Long ssn;
    private String position;
    private LocalDateTime createdAt;
    private Boolean enabled = true;
    private Boolean locked = false;

    @JsonIgnore
    @OneToOne(cascade = ALL, orphanRemoval = true)
    private ConfirmationToken confirmationToken;

    @JsonIgnore
    @OneToOne(cascade = ALL, mappedBy = "employee")
    private EmployeeImage employeeImage;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendances = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Leaves> leaves = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Paystub> paystubs = new ArrayList<>();

    @ManyToOne(cascade = ALL)
    @JoinColumn(
            name = "department_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "department_id_fk"))
    private Department department;

    public void setDepartment(Optional<Department> department) {
        this.department = department.get();
        department.get().getEmployees().add(this);
    }

    @JsonIgnore
    @ManyToMany(cascade = ALL, fetch = EAGER)
    @JoinTable(
            name = "employee_roles",
            joinColumns = @JoinColumn(
                    name = "employee_id",
                    foreignKey = @ForeignKey(name = "employee_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    foreignKey = @ForeignKey(name = "role_id_fk")
            )
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
        role.getEmployees().add(this);
    }

    public Employee(String firstName, String lastName, String email, Long phone, Long ssn, String position, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.ssn = ssn;
        this.position = position;
        this.createdAt = createdAt;
    }

    public String getEmployeeImage() {
        return this.employeeImage.getImageUrl();
    }
}

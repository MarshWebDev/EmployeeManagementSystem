package management.employee.Application.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private LocalDateTime createdAt;
    private Boolean enabled = false;
    private Boolean locked = false;

    @OneToOne(cascade = ALL, orphanRemoval = true)
    private ConfirmationToken confirmationToken;

    @OneToOne(cascade = ALL, mappedBy = "employee")
    private EmployeeImage employeeImage;

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendances = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Leaves> leaves = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Paystub> paystubs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "department_id_fk"))
    private Department department;

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
        roles.add(role);
        role.getEmployees().add(this);
    }

    public Employee(String firstName, String lastName, String email, Long phone, Long ssn, LocalDateTime createdAt, Boolean enabled, Boolean locked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.ssn = ssn;
        this.createdAt = createdAt;
        this.enabled = enabled;
        this.locked = locked;
    }
}

package management.employee.Application.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter @Setter @NoArgsConstructor
@Entity(name = "Employee")
@Table(
        name = "employee",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique_constraint",
                columnNames = "email"
        )
)
public class Employee implements UserDetails {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private Long phone;
    @Column(unique = true)
    private Long ssn;
    private String username;
    private String password;
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

    public Employee(String firstName, String lastName, String email, Long phone, Long ssn, String username, String password, LocalDateTime createdAt, Boolean enabled, Boolean locked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.ssn = ssn;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.enabled = enabled;
        this.locked = locked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
            role.getPermissions().forEach(permission ->
                    authorities.add(new SimpleGrantedAuthority(permission.getName())));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

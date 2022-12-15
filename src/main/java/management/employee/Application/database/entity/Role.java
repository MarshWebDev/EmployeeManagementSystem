package management.employee.Application.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter @NoArgsConstructor
@Entity
@Table
public class Role {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(cascade = ALL, fetch = EAGER)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(
                    name = "role_id",
                    foreignKey = @ForeignKey(name = "role_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "permission_id",
                    foreignKey = @ForeignKey(name = "permission_id_fk")
            )
    )
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    private Set<Employee> employees = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
        permission.getRoles().add(this);
    }
}

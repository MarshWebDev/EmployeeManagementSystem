package management.employee.Application.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter @NoArgsConstructor
@Entity
@Table
public class Permission {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    public Permission(String name) {
        this.name = name;
    }
}

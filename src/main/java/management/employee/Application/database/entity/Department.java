package management.employee.Application.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter @NoArgsConstructor
@Entity
@Table
public class Department {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(cascade = ALL)
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private List<Wage> wages = new ArrayList<>();


}

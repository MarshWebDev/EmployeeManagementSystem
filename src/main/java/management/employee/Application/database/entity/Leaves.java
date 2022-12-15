package management.employee.Application.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter @NoArgsConstructor
@Entity
@Table
public class Leaves {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(cascade = ALL)
    @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "employee_id_fk")
    )
    private Employee employee;

    private LocalDate leftAt;
    private LocalDate returnedAt;
    private String reason;

    public Leaves(Employee employee, LocalDate leftAt, LocalDate returnedAt, String reason) {
        this.employee = employee;
        this.leftAt = leftAt;
        this.returnedAt = returnedAt;
        this.reason = reason;
    }
}

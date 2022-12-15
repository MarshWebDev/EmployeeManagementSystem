package management.employee.Application.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter @NoArgsConstructor
@Entity
@Table
public class EmployeeImage {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "employee_id_fk")
    )
    private Employee employee;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    public EmployeeImage(Employee employee, String imageUrl) {
        this.employee = employee;
        this.imageUrl = imageUrl;
    }
}

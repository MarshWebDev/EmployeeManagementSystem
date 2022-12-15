package management.employee.Application.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter @NoArgsConstructor
@Entity
@Table
public class ConfirmationToken {
    @Id @GeneratedValue( strategy = IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "employee_id_fk")
    )
    private Employee employee;

    @Column(unique = true, columnDefinition = "TEXT")
    private String token;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime expiresAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime confirmedAt;

    public ConfirmationToken(Employee employee, String token, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.employee = employee;
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}

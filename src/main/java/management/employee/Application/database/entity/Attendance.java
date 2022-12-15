package management.employee.Application.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter @NoArgsConstructor
@Entity
@Table
public class Attendance {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(cascade = ALL)
    @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "employee_id_fk")
    )
    private Employee employee;
    private LocalDate date;
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;
    private Double totalHours;

    public Attendance(Employee employee, LocalDate date, LocalDateTime clockIn, LocalDateTime clockOut, Double totalHours) {
        this.employee = employee;
        this.date = date;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.totalHours = totalHours;
    }
}

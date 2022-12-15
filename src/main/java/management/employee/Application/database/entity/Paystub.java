package management.employee.Application.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter @NoArgsConstructor
@Entity
@Table
public class Paystub {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "employee_id_fk")
    )
    private Employee employee;

    private String company;
    private LocalDate paidOn;
    private Double payRate;
    private Double totalHours;
    private Double ytdHours;
    private Double totalPay;
    private Double ytdPay;

    public Paystub(Employee employee, String company, LocalDate paidOn, Double payRate, Double totalHours, Double ytdHours, Double totalPay, Double ytdPay) {
        this.employee = employee;
        this.company = company;
        this.paidOn = paidOn;
        this.payRate = payRate;
        this.totalHours = totalHours;
        this.ytdHours = ytdHours;
        this.totalPay = totalPay;
        this.ytdPay = ytdPay;
    }
}

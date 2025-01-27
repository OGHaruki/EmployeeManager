package pl.edu.pg.aui.employee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @ToString.Exclude
    @Builder.Default
    private UUID uuid = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "seniority", nullable = false)
    String seniority;

    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;
}

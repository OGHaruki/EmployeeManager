package pl.edu.pg.aui.demo.entity;

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
    @JoinColumn(name = "profession_id")
    Company company;
}

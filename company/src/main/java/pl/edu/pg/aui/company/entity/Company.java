package pl.edu.pg.aui.company.entity;

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
@Table(name = "companies")
public class Company {

    @Id
    @ToString.Exclude
    @Builder.Default
    private UUID uuid = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "sector", nullable = false)
    String sector;
}

package pl.edu.pg.aui.employee.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setCompany(this);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setCompany(null);
    }
}

package entity;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude="employees")
@EqualsAndHashCode
public class Company implements Comparable<Company>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String sector;

    @EqualsAndHashCode.Exclude
    private List<Employee> employees;

    @Override
    public int compareTo(Company o) {
        int nameComparison = this.name.compareTo(o.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.sector.compareTo(o.sector);
    }
}

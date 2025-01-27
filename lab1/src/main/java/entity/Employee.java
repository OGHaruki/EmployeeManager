package entity;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "company")
@EqualsAndHashCode
public class Employee implements Comparable<Employee>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String seniority;

    @EqualsAndHashCode.Exclude
    private Company company;


    @Override
    public int compareTo(Employee o) {
        if(this.name.compareTo(o.name) != 0){
            return this.name.compareTo(o.name);
        } else if (this.seniority.compareTo(o.seniority) != 0){
            return this.seniority.compareTo(o.seniority);
        } else {
            return this.company.compareTo(o.company);
        }
    }
}

package dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class EmployeeDto implements Comparable<EmployeeDto>{
    private String name;
    private String seniority;
    private String companyName;

    @Override
    public int compareTo(EmployeeDto o) {
        if (this.name.compareTo(o.name) != 0) {
            return this.name.compareTo(o.name);
        } else if (this.seniority.compareTo(o.seniority) != 0) {
            return this.seniority.compareTo(o.seniority);
        } else {
            return this.companyName.compareTo(o.companyName);
        }
    }
}

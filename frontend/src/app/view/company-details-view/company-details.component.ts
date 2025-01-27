import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Company } from '../../api/company/model/company';
import { Employee } from '../../api/company/model/employee';
import { CompanyService } from '../../api/company/service/company.service';
import { EmployeeService } from '../../api/company/service/employee.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-company-details',
  standalone: true,
  templateUrl: './company-details.component.html',
  styleUrls: ['./company-details.component.css'],
  imports: [CommonModule]
})
export class CompanyDetailsComponent implements OnInit {

    company: Company | undefined;
    employees: Employee[] = []; 
    successMessage: string = '';
    errorMessage: string = '';

    constructor(
        private companyService: CompanyService,
        private employeeService: EmployeeService,
        private route: ActivatedRoute,
        private router: Router
      ) { }

    addEmployee(): void {
        this.router.navigate(['/companies/' + this.company?.name + '/add-employee']);
    }

    editEmployee(employeeName: string): void {
        if (this.company) {
            const encodedCompanyName = encodeURIComponent(this.company.name);
            const encodedEmployeeName = encodeURIComponent(employeeName);
            console.log(`Editing employee: ${encodedEmployeeName}` + ` for company: ${encodedCompanyName}`);
            this.router.navigate(['/companies', encodedCompanyName, 'employees', encodedEmployeeName, 'edit']);
        }
      }

    employeeDetails(employeeName: string): void {
        if (this.company && this.company.name && employeeName) {
            const encodedCompanyName = encodeURIComponent(this.company.name);
            const encodedEmployeeId = encodeURIComponent(employeeName);
            this.router.navigate(['/companies', encodedCompanyName, 'employees', encodedEmployeeId, 'details']);
          }
    }

    loadEmployees(companyName: string): void {
        this.employeeService.getEmployeesByCompany(companyName).subscribe(
            (response) => {
                this.employees = response.employees;
        }, 
        (error) => {
            this.errorMessage = error.error ? error.error.message : 'Error loading employees';
        });
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            const companyName = params['name'];
            this.companyService.getCompanyByName(companyName).subscribe(
                (company) => {
                    this.company = company;
                    this.loadEmployees(companyName);
                },
                (error) => {
                    this.errorMessage = error.error ? error.error.message : 'Error loading company details';
                }
            );
        });
    }

    deleteEmployee(company: Company, employee: Employee): void {
        console.log(`Deleting employee: ${employee.name}`);
        this.employeeService.deleteEmployee(company.name, employee.name).subscribe({
            next: () => {
                console.log(`Deleted employee: ${employee.name}`);
                this.employees = this.employees.filter(e => e.name !== employee.name);
                this.successMessage = `Deleted employee: ${employee.name}`;
            },
            error: (err) => {
                this.errorMessage = `Failed to delete employee: ${employee.name}`;
            }
        });
    }
}
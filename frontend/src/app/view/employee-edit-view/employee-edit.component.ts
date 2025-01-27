import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../../api/company/service/employee.service';
import { Employee } from '../../api/company/model/employee';
import { FormsModule } from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-edit-employee',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css'],
  imports: [FormsModule, CommonModule]
})
export class EditEmployeeComponent implements OnInit {

  companyName: string = '';
  employeeName: string = '';

  employee: Employee = {
    name: '',
    seniority: '',
  }

  original: Employee | undefined;
  
  constructor(
    private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.companyName = params['companyName'];
      this.employeeName = params['employeeName'];
      console.log(`Editing employee: ${this.employeeName}` + ` for company: ${this.companyName}`);
      this.employeeService.getEmployeeByName(this.companyName, this.employeeName)
        .subscribe(employee => {
            this.employee = {
                name: employee.name,
                seniority: employee.seniority,
            };
            this.original = {...this.employee};
        });
    });
  }

  onSubmit(): void {
    this.employeeService.updateEmployee(this.companyName, this.employeeName, this.employee)
      .subscribe(() => this.router.navigate(['/companies', this.companyName, 'details']));
  }

  onReset(form: any): void {
    form.resetForm(this.original);
  }
}
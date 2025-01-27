import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EmployeeService } from '../../api/company/service/employee.service';
import { Employee } from '../../api/company/model/employee';

@Component({
  standalone: true,
  selector: 'app-add-employee',
  templateUrl: './employee-add.component.html',
  styleUrls: ['./employee-add.component.css'],
  imports: [CommonModule, FormsModule]
})
export class AddEmployeeComponent {

  companyName: string = '';
  newEmployee: Employee = { name: '', seniority: '' };
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.companyName = params['name'];
    });
  }

  onSubmit(): void {
    this.employeeService.addEmployee(this.companyName, this.newEmployee).subscribe({
      next: (response) => {
        console.log('Employee added successfully:', response);
        this.successMessage = 'Employee added successfully';
        this.router.navigate(['/companies/' + this.companyName + '/details']);
      },
      error: (err) => {
        console.error('Error while adding employee:', err);
        this.errorMessage = 'Failed to add employee';
      }
    });
  }
}
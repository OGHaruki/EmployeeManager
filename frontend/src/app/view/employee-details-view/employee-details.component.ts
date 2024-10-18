import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../../api/company/service/employee.service';
import { Employee } from '../../api/company/model/employee';

@Component({
  standalone: true,
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css'],
  imports: [CommonModule]
})
export class EmployeeDetailsComponent implements OnInit {

  companyName: string = '';
  employeeName: string = '';
  employee: Employee | undefined;

  constructor(
    private employeeService: EmployeeService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.companyName = params['companyName'];
      this.employeeName = params['employeeName'];
      this.loadEmployeeDetails();
    });
  }

  loadEmployeeDetails(): void {
    this.employeeService.getEmployeeByName(this.companyName, this.employeeName).subscribe(
      (employee) => {
        this.employee = employee;
      },
      (error) => {
        console.error('Error loading employee details:', error);
      }
    );
  }
}
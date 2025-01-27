import { Inject, Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Employees } from '../model/employees';
import { Employee } from '../model/employee';

@Injectable({
    providedIn: 'root'
})
export class EmployeeService {
    constructor(private http: HttpClient) {
    }

    getEmployeesByCompany(companyName: string): Observable<Employees> {
        return this.http.get<Employees>('/api/companies/' + companyName + '/employees');
    } 

    getEmployeeById(companyName: string, employeeId: string): Observable<Employee> {
        return this.http.get<Employee>('/api/companies/' + companyName + '/employees/' + employeeId);
    }

    getEmployeeByName(companyName: string, employeeName: string): Observable<Employee> {
        return this.http.get<Employee>('/api/companies/' + companyName + '/employees/' + employeeName);
    }

    deleteEmployee(companyName: string, employeeName: string): Observable<void> {
        return this.http.delete<void>(`/api/companies/${companyName}/employees/${employeeName}`);
    }

    addEmployee(companyName: string, employee: Employee): Observable<Employee> {
        return this.http.put<Employee>('/api/companies/' + companyName + '/employees', employee);
    }

    updateEmployee(companyName: string, employeeId: string, employee: Employee): Observable<Employee> {
        return this.http.patch<Employee>('/api/companies/' + companyName + '/employees/' + employeeId, employee);
    }
}

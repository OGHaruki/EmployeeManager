import { Routes } from '@angular/router';
import { CompanyListComponent } from './view/company-list-view/company-list.component';
import { MainComponent } from './component/main/main.component';
import { AddCompanyComponent } from './view/company-add-view/add-company.component';
import { CompanyEditComponent } from './view/company-edit-view/edit-company.component';
import { CompanyDetailsComponent } from './view/company-details-view/company-details.component';
import { AddEmployeeComponent } from './view/employee-add-view/employee-add.component';
import { EditEmployeeComponent } from './view/employee-edit-view/employee-edit.component';
import { EmployeeDetailsComponent } from './view/employee-details-view/employee-details.component';

export const routes: Routes = [
    {
        component: MainComponent,
        path: ''
    },
    {
        component: CompanyListComponent,
        path: 'companies'
    },
    {
        component: AddCompanyComponent,
        path: 'add-company'
    },
    {
        component: CompanyEditComponent,
        path: 'companies/edit/:name'
    },
    {
        component: CompanyDetailsComponent,
        path: 'companies/:name/details'
    },
    {
        component: AddEmployeeComponent,
        path: 'companies/:name/add-employee'
    },
    {
        component: EditEmployeeComponent,
        path: 'companies/:companyName/employees/:employeeName/edit'
    },
    {
        component: EmployeeDetailsComponent,
        path: 'companies/:companyName/employees/:employeeName/details' // Dodaj ścieżkę do widoku szczegółów pracownika
    }
];

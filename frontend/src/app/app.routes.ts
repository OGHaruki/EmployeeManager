import { Routes } from '@angular/router';
import { CompanyListComponent } from './view/company-list-view/company-list.component';
import { MainComponent } from './component/main/main.component';
import { AddCompanyComponent } from './view/company-add-view/add-company.component';

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
    }
];

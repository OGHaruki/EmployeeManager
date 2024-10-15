import { Routes } from '@angular/router';
import { CompanyListComponent } from './view/company-list-view/company-list.component';

export const routes: Routes = [
    {
        component: CompanyListComponent,
        path: 'companies'
    }
];

import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CompanyService } from '../../api/company/service/company.service';
import { Company } from '../../api/company/model/company';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-add-company',
    standalone: true,
    templateUrl: './add-company.component.html',
    styleUrls: ['./add-company.component.css'],
    imports: [CommonModule, FormsModule]
})
export class AddCompanyComponent {
    newCompany: Company = {
        name: '',
        sector: ''
      };

    successMessage: string = '';
    errorMessage: string = '';

    constructor(private service: CompanyService, private router: Router) { }

    onSubmit(company: Company): void {
        console.log('Submitting company:', company);
        this.service.addCompany(company).subscribe({
            next: (response) => {
                console.log('Company added successfully:', response);
                this.successMessage = `Company ${company.name} added successfully`;
                this.errorMessage = '';
                this.router.navigate(['/companies']);
            },
            error: (err) => {
                console.error('Error while adding company:', err);
                this.errorMessage = `Error while adding company ${company.name}`;
                this.successMessage = '';
            }
        });
    }
}
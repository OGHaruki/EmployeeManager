import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../../api/company/service/company.service';
import { Companies } from '../../api/company/model/companies';
import { Company } from '../../api/company/model/company';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
    selector: 'app-company-list',
    standalone: true,
    templateUrl: './company-list.component.html',
    styleUrl: './company-list.component.css',
    imports: [CommonModule, RouterLink]
})
export class CompanyListComponent implements OnInit {
    constructor(private service: CompanyService) { }

    companies: Companies | undefined;

    ngOnInit(): void {
        this.service.getCompanies().subscribe((companies) => { this.companies = companies });
    }

    onDelete(company: Company): void {
        this.service.deleteCompany(company.name).subscribe(() => this.ngOnInit)
    }
}
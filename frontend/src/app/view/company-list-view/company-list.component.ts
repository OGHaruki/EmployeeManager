import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../../api/company/service/company.service';
import { Company } from '../../api/company/model/company';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-company-list',
  standalone: true,
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css'],
  imports: [CommonModule, RouterLink]
})
export class CompanyListComponent implements OnInit {
  companies: Company[] = [];

  constructor(private service: CompanyService) { }

  ngOnInit(): void {
    this.loadCompanies();
  }

  loadCompanies(): void {
    this.service.getCompanies().subscribe((companies) => {
      this.companies = companies.companies;
    });
  }

  onDelete(company: Company): void {
    console.log(`Deleting company: ${company.name}`);
    this.service.deleteCompany(company.name).subscribe({
      next: () => {
        console.log(`Deleted company: ${company.name}`);
        this.companies = this.companies.filter(c => c.name !== company.name);
      },
      error: (err) => {
        console.error(`Failed to delete company: ${company.name}`, err);
      }
    });
  }
}
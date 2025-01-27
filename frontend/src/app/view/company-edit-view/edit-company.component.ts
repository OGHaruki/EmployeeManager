import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CompanyService } from "../../api/company/service/company.service";
import { Company } from '../../api/company/model/company';
import { CompanyForm } from '../../api/company/model/companyForm';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-edit-company',
  templateUrl: './edit-company.component.html',
  styleUrls: ['./edit-company.component.css'],
  imports: [FormsModule, CommonModule]
})
export class CompanyEditComponent implements OnInit {

  companyName: string = '';

  company: CompanyForm = {
    name: '',
    sector: ''
  }

  original: Company | undefined;
  
  constructor(
    private companyService: CompanyService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.companyName = params['name'];
      this.companyService.getCompanyByName(this.companyName!)
        .subscribe(company => {
            this.company = {
                name: company.name,
                sector: company.sector
            };
            this.original = {...this.company};
        });
        });
    }


  onSubmit(): void {
    this.companyService.updateCompany(this.companyName!, this.company!)
      .subscribe(() => this.router.navigate(['/companies']));
  }

 
  onReset(form: any): void {
    form.resetForm(this.original);
  }
}

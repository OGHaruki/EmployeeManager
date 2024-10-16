import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Companies } from '../model/companies';
import { Company } from '../model/company';

@Injectable(
    {
        providedIn: 'root'
    }
)
export class CompanyService {
    constructor(private http: HttpClient) {
    }

    getCompanies(): Observable<Companies> {
        return this.http.get<Companies>('/api/companies');
    }

    deleteCompany(name: string): Observable<void> {
        return this.http.delete<void>(`/api/companies/${name}`);
    }

    addCompany(company: Company): Observable<void> {
        return this.http.put<void>('/api/companies', company);
    }

    updateCompany(company: Company): Observable<void> {
        return this.http.put<void>('/api/companies/' + company.name, company);
    }
}

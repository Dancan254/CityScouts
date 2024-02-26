package com.jobs.cityscouts.service;

import com.jobs.cityscouts.entity.companyEntity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    void createCompany(Company company);

    void updateCompanyDetails(Long id, Company company);

    Company findCompanyById(Long id);
}

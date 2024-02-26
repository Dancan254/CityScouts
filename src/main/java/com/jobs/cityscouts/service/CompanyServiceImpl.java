package com.jobs.cityscouts.service;

import com.jobs.cityscouts.entity.companyEntity.Company;
import com.jobs.cityscouts.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    CompanyRepository companyRepository;
    @Override
    public List<Company> getAllCompanies() {
        try {
            return companyRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>(); //returns an empty list
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void updateCompanyDetails(Long id, Company updatedCompany) {
        //check if it exists
        if (companyRepository.existsById(id)){
            Company company = new Company();
            company.setName(updatedCompany.getName());
            company.setCompanyDescription(updatedCompany.getCompanyDescription());
            company.setLocation(updatedCompany.getLocation());
            companyRepository.save(company);
        }
    }

    @Override
    public Company findCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }
}

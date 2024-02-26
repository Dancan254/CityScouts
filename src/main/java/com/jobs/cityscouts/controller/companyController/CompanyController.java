package com.jobs.cityscouts.controller.companyController;

import com.jobs.cityscouts.entity.companyEntity.Company;
import com.jobs.cityscouts.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @GetMapping("all")
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long id) {
        try {
            Company company = companyService.findCompanyById(id);
            return ResponseEntity.ok(company);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Successfully created the company", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompanyDetails(@PathVariable Long id, @RequestBody Company company){
        companyService.updateCompanyDetails(id,company);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }
}

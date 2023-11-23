package kg.rakhim.classes.jobsearchapp.services;


import kg.rakhim.classes.jobsearchapp.entities.Company;
import kg.rakhim.classes.jobsearchapp.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CompanyService {
    private final CompanyRepository compRep;

    @Autowired
    public CompanyService(CompanyRepository compRep) {
        this.compRep = compRep;
    }

    public List<Company> getAllCompanies(){
        return compRep.findAll();
    }

    public Company getCompany(int id){
        Optional<Company> company = compRep.findById(id);
        return company.orElse(null);
    }

    @Transactional
    public void createCompany(Company company){
        compRep.save(company);
    }

    @Transactional
    public void deleteCompany(int id){
        compRep.deleteById(id);
    }

    public List<Company> findByName(String name){
        if(compRep.findByNameStartingWith(name) == null){
            return Collections.emptyList();
        }else {
            char firstLetter = Character.toUpperCase(name.charAt(0));
            String restOf = name.substring(1);
            String finalString = firstLetter + restOf;
            return compRep.findByNameStartingWith(finalString);

        }
    }

    @Transactional
    public void updateCompany(int id, Company updated){
        Company toUpdate = compRep.findById(id).orElse(null);
        if(toUpdate != null) {
            toUpdate.setEmail(updated.getEmail());
            toUpdate.setId(updated.getId());
            toUpdate.setName(updated.getName());
//            toUpdate.setVacancies(updated.getVacancies());
            compRep.save(toUpdate);
        }
    }

    public Optional<Company> findByEmail(String email){
        return compRep.findByEmail(email);
    }
}

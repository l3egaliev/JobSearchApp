package kg.rakhim.classes.jobsearchapp.utils;

import kg.rakhim.classes.jobsearchapp.entities.Company;
import kg.rakhim.classes.jobsearchapp.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailValidator implements Validator {
    private final CompanyService service;

    @Autowired
    public EmailValidator(CompanyService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Company.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Company company = (Company) target;
        if(service.findByEmail(company.getEmail()).isPresent()){
            errors.rejectValue("email", "", "Такой адрес электронный почты уже зарегистрирован!");
        }
    }
}

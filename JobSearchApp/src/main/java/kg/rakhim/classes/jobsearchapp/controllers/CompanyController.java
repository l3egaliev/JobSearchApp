package kg.rakhim.classes.jobsearchapp.controllers;

import jakarta.validation.Valid;
import kg.rakhim.classes.jobsearchapp.dto.CompanyDTO;
import kg.rakhim.classes.jobsearchapp.entities.Company;
import kg.rakhim.classes.jobsearchapp.services.CompanyService;
import kg.rakhim.classes.jobsearchapp.utils.CreateException;
import kg.rakhim.classes.jobsearchapp.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService compService;
    private final EmailValidator emailValidator;

    @Autowired
    public CompanyController(CompanyService compService, EmailValidator emailValidator) {
        this.compService = compService;
        this.emailValidator = emailValidator;
    }



    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAll(){
        List<CompanyDTO> res = new ArrayList<>();
        for (Company company : compService.getAllCompanies()){
            CompanyDTO dto = CompanyDTO.convertToDto(company);
            res.add(dto);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getOne(@PathVariable("id") int id){
        CompanyDTO founded = CompanyDTO.convertToDto(compService.getCompany(id));
        return new ResponseEntity<>(founded, HttpStatus.OK);
    }

    @PostMapping("/create")
    @CrossOrigin(value = "http://127.0.0.1:5500")
    public ResponseEntity<HttpStatus> createCompany(@RequestBody @Valid CompanyDTO company, BindingResult br){
        emailValidator.validate(CompanyDTO.convertToModel(company), br);
        if(br.hasErrors()){
            StringBuilder builder = new StringBuilder();
            List<FieldError> errors = br.getFieldErrors();
            for(FieldError er : errors){
                builder.append(er.getField()).append("-").append(er.getDefaultMessage()).append(";");

            }
            throw new CreateException(builder.toString());
        }
        compService.createCompany(CompanyDTO.convertToModel(company));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<CreateException> handCreateExp(CreateException ex){
        CreateException exception = new CreateException(ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }





}

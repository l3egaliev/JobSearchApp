package kg.rakhim.classes.jobsearchapp.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import kg.rakhim.classes.jobsearchapp.dto.CompanyDTO;
import kg.rakhim.classes.jobsearchapp.dto.VacancyDTO;
import kg.rakhim.classes.jobsearchapp.entities.Company;
import kg.rakhim.classes.jobsearchapp.entities.Vacancy;
import kg.rakhim.classes.jobsearchapp.services.VacancyService;
import kg.rakhim.classes.jobsearchapp.utils.CreateException;
import kg.rakhim.classes.jobsearchapp.utils.NotFoundException;
import kg.rakhim.classes.jobsearchapp.utils.UpdateException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vacancies")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class VacancyController {
    public final VacancyService vService;

    @Autowired
    public VacancyController(VacancyService vService) {
        this.vService = vService;
    }

    @GetMapping("/{page}/{vacancy_per_page}")
    public List<Vacancy> getAllWithPagination(@PathVariable(value = "page", required = false) Integer page,
                                @PathVariable(value = "vacancy_per_page", required = false) Integer vacancy_per_page){
            if (page == null && vacancy_per_page == null){
                return vService.getAllVacancies();
            }else {
                return vService.getAllWithPagination(page, vacancy_per_page);
            }
    }

    @GetMapping
    public ResponseEntity<List<VacancyDTO>> getAll(){
        List<VacancyDTO> res = new ArrayList<>();
        for (Vacancy vacancy : vService.getAllVacancies()){
            VacancyDTO dto = VacancyDTO.convertToDto(vacancy);
            res.add(dto);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<VacancyDTO> getOne(@PathVariable("id") int id){
        if(vService.getVacancy(id) == null){
            throw new NotFoundException("Вакансия не найдена");
        }
        Vacancy vacancy = vService.getVacancy(id);
        VacancyDTO dto = VacancyDTO.convertToDto(vacancy);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<NotFoundException> handCreateExp(NotFoundException ex){
        NotFoundException exception = new NotFoundException(ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/create")
    public ResponseEntity<Vacancy> createVacancy(@RequestBody @Valid VacancyDTO v, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder builder = new StringBuilder();
            List<FieldError> errors;
            errors = bindingResult.getFieldErrors();
            for(FieldError er : errors){
                builder.append(er.getField()).append(" - ").append(er.getDefaultMessage());
            }
            throw new CreateException(builder.toString());
        }
        Vacancy vacancy  = VacancyDTO.convertToVacancy(v);
        addOtherFields(vacancy);
        vService.createVacancy(vacancy);
        return new ResponseEntity<>(vService.getVacancy(vacancy.getId()), HttpStatus.OK);
    }


    @ExceptionHandler
    public ResponseEntity<CreateException> handCreateExp(CreateException ex){
        CreateException exception = new CreateException(ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/search/{name}")
    public List<VacancyDTO> searchByName(@PathVariable(value = "name", required = false)
                                                String name){
        List<VacancyDTO> res = new ArrayList<>();
        for(Vacancy vacancy : vService.findByName(name)){
            res.add(VacancyDTO.convertToDto(vacancy));
        }

        return res;
    }

    @DeleteMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        vService.deleteVacancy(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid VacancyDTO dto,
                                             BindingResult br){
        if(br.hasErrors()){
            StringBuilder builder = new StringBuilder();
            List<FieldError> errors = br.getFieldErrors();
            for(FieldError er : errors){
                builder.append(er.getField()).append(" - ").append(er.getDefaultMessage());
            }
            throw new UpdateException(builder.toString());
        }
        Vacancy vacancy = VacancyDTO.convertToVacancy(dto);
        vService.updateVacancy(id,vacancy);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<UpdateException> handUpdateException(UpdateException e){
        UpdateException ex = new UpdateException(e.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    private void addOtherFields(Vacancy vacancy){
        vacancy.setCreatedAt(new Date());
        vacancy.setCompany_id(1);
    }

}

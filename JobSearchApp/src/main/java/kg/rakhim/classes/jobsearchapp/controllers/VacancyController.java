package kg.rakhim.classes.jobsearchapp.controllers;

import kg.rakhim.classes.jobsearchapp.entities.Vacancy;
import kg.rakhim.classes.jobsearchapp.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
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
    public List<Vacancy> getAll(){
        return vService.getAllVacancies();
    }


    @GetMapping("/{id}")
    public Vacancy getOne(@PathVariable("id") int id){
        return vService.getVacancy(id);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping
    public ResponseEntity<HttpStatus> createVacancy(@RequestBody Vacancy v){
        vService.createVacancy(v);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public List<Vacancy> searchByName(@PathVariable(value = "name", required = false)
                                                String name){
        return vService.findByName(name);
    }

    @DeleteMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        vService.deleteVacancy(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}

package kg.rakhim.classes.jobsearchapp.services;

import kg.rakhim.classes.jobsearchapp.entities.User;
import kg.rakhim.classes.jobsearchapp.entities.Vacancy;
import kg.rakhim.classes.jobsearchapp.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class VacancyService {
    private final VacancyRepository vrep;

    @Autowired
    public VacancyService(VacancyRepository vrep) {
        this.vrep = vrep;
    }

    public List<Vacancy> getAllVacancies(){
        return vrep.findAll();
    }

    public List<Vacancy> getAllWithPagination(int page, int vacancy_per_page){
            return vrep.findAll(PageRequest.of(page, vacancy_per_page, Sort.by("id"))).getContent();
    }
    public Vacancy getVacancy(int urlId){
        Optional<Vacancy> vacancy = vrep.findById(urlId);
        return vacancy.orElse(null);
    }

    @Transactional
    public void createVacancy(Vacancy vacancy){
        vacancy.setCreatedAt(new Date());
        vrep.save(vacancy);
    }

    @Transactional
    public void deleteVacancy(int id){
        vrep.deleteById(id);
    }

    public List<Vacancy> findByName(String name){
        if(vrep.findByNameStartingWith(name) == null){
            return Collections.emptyList();
        }else {
            char firstLetter = Character.toUpperCase(name.charAt(0));
            String restOf = name.substring(1).toLowerCase();
            String finalString = firstLetter + restOf;
            return vrep.findByNameStartingWith(finalString);

        }
    }
}

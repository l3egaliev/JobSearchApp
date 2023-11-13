package kg.rakhim.classes.jobsearchapp.repositories;

import kg.rakhim.classes.jobsearchapp.entities.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    List<Vacancy> findByNameStartingWith(String name);
}

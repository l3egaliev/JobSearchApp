package kg.rakhim.classes.jobsearchapp.repositories;

import kg.rakhim.classes.jobsearchapp.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<Company> findByNameStartingWith(String name);
    Optional<Company> findByEmail(String email);
}

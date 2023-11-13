package kg.rakhim.classes.jobsearchapp.repositories;

import kg.rakhim.classes.jobsearchapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

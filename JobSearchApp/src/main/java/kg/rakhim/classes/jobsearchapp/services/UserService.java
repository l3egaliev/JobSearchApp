package kg.rakhim.classes.jobsearchapp.services;

import kg.rakhim.classes.jobsearchapp.entities.User;
import kg.rakhim.classes.jobsearchapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(int id){
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Transactional
    public void createUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
}

package backend.project.service;

import backend.project.dto.auth.RegisterRequest;
import backend.project.exception.*;
import backend.project.model.User;
import backend.project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(RegisterRequest request) throws AlreadyExistException {
        if(userRepository.findByUsername(request.getUsername()).isPresent())
            throw new AlreadyExistException("Username already exists!");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole().toUpperCase());
        userRepository.save(user);
        return "User registered successfully";
    }
}

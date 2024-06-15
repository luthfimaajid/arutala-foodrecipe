package arutala.backend.bookrecipe.service;

import arutala.backend.bookrecipe.model.User;
import arutala.backend.bookrecipe.dto.request.SignUpRequest;
import arutala.backend.bookrecipe.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User signUp(SignUpRequest signUpRequest) {
        final String defaultRole = "User";

        if (!signUpRequest.getPassword().equals(signUpRequest.getRetypePassword())) {
            // throw bad req

            return null;
        }

        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .fullname(signUpRequest.getFullname())
                .password(hashedPassword)
                .role(defaultRole)
                .isDeleted(Boolean.FALSE)
                .build();

        try {
            user = userRepository.save(user);
            log.info(user.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return  user;
    }
}

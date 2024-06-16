package arutala.backend.bookrecipe.service;

import arutala.backend.bookrecipe.dto.request.SignInRequest;
import arutala.backend.bookrecipe.dto.response.SignInResponse;
import arutala.backend.bookrecipe.model.User;
import arutala.backend.bookrecipe.dto.request.SignUpRequest;
import arutala.backend.bookrecipe.repository.UserRepository;
import arutala.backend.bookrecipe.util.Jwt;
import arutala.backend.bookrecipe.util.ResponseMessage;
import arutala.backend.bookrecipe.util.exception.BadRequestException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Jwt jwt;

    @Transactional
    public User signUp(SignUpRequest signUpRequest) {
        final String defaultRole = "User";

        if (!signUpRequest.getPassword().equals(signUpRequest.getRetypePassword())) {
            throw new BadRequestException(ResponseMessage.Failed.PASSWORD_CONFIRMATION_MISSMATCH);
        }

        Optional<User> existUser = userRepository.findAllByUsername(signUpRequest.getUsername()).stream().findFirst();
        if (existUser.isPresent()) {
            throw new EntityExistsException(ResponseMessage.Failed.USERNAME_ALREADY_REGISTERED);
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
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }

        return user;
    }

    @Transactional
    public SignInResponse signIn(SignInRequest signInRequest) {
        Optional<User> user = userRepository.findByUsername(signInRequest.getUsername());

        if (!user.isPresent()) {
            throw new EntityNotFoundException(ResponseMessage.Failed.USERNAME_NOT_FOUND);
        }

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.get().getPassword())) {
            throw new EntityNotFoundException(ResponseMessage.Failed.WRONG_PASSWORD);
        }

        User u = user.get();

        String token = jwt.generateToken(u);

        return SignInResponse.builder()
                .id(u.getId())
                .username(u.getUsername())
                .role(u.getRole())
                .token(token)
                .type("ACCESS TOKEN")
                .build();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }
}

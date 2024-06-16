package arutala.backend.bookrecipe.controller;

import arutala.backend.bookrecipe.dto.response.SignInResponse;
import arutala.backend.bookrecipe.model.User;
import arutala.backend.bookrecipe.dto.request.SignInRequest;
import arutala.backend.bookrecipe.dto.request.SignUpRequest;
import arutala.backend.bookrecipe.dto.response.BaseResponse;
import arutala.backend.bookrecipe.service.UserService;
import arutala.backend.bookrecipe.util.ResponseHandler;
import arutala.backend.bookrecipe.util.ResponseMessage;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user-management/users")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest signInRequest) {
        SignInResponse signInResponse = userService.signIn(signInRequest);
        return ResponseHandler.ok(ResponseMessage.Success.DEFAULT, signInResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        User user = userService.signUp(signUpRequest);
        return ResponseHandler.created(String.format(ResponseMessage.Success.USER_CREATED, user.getUsername()));
    }
}

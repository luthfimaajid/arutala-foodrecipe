package arutala.backend.bookrecipe.controller;

import arutala.backend.bookrecipe.model.User;
import arutala.backend.bookrecipe.model.dto.request.SignInRequest;
import arutala.backend.bookrecipe.model.dto.request.SignUpRequest;
import arutala.backend.bookrecipe.model.dto.response.BaseResponse;
import arutala.backend.bookrecipe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user-management/users")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest signInRequest) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest signUpRequest) {
        User user = userService.signUp(signUpRequest);

        BaseResponse baseResponse = BaseResponse.builder()
                .message(String.format("User %s registered successfully!", user.getUsername()))
                .statusCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .build();

        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }
}

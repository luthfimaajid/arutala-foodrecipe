package arutala.backend.bookrecipe.controller;

import arutala.backend.bookrecipe.model.User;
import arutala.backend.bookrecipe.dto.request.SignInRequest;
import arutala.backend.bookrecipe.dto.request.SignUpRequest;
import arutala.backend.bookrecipe.dto.response.BaseResponse;
import arutala.backend.bookrecipe.service.UserService;
import arutala.backend.bookrecipe.util.ResponseHandler;
import arutala.backend.bookrecipe.util.ResponseMessage;
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

    @GetMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest signInRequest) throws BadRequestException {
        throw new BadRequestException("data tidak lengkap");
//        try {
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest signUpRequest) {
        User user = userService.signUp(signUpRequest);

        return ResponseHandler.created(String.format(ResponseMessage.Success.USER_CREATED, user.getUsername()));
    }
}

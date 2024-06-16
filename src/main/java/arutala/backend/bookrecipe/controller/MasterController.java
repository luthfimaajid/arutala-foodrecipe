package arutala.backend.bookrecipe.controller;

import arutala.backend.bookrecipe.model.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-recipe-masters")
@Slf4j
public class MasterController {
    @GetMapping("/category-option-lists")
    public ResponseEntity<Object> getCategories() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        log.info(userDetails.toString());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/level-option-lists")
    public ResponseEntity<Object> getLevels() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        log.info(userDetails.toString());
        return ResponseEntity.ok().build();
    }
}

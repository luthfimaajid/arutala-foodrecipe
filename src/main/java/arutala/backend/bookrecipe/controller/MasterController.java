package arutala.backend.bookrecipe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-recipe-masters")
public class MasterController {
    @GetMapping("/category-option-lists")
    public ResponseEntity<Object> getCategories() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/level-option-lists")
    public ResponseEntity<Object> getLevels() {
        return ResponseEntity.ok().build();
    }
}

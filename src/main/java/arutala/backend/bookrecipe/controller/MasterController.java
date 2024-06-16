package arutala.backend.bookrecipe.controller;

import arutala.backend.bookrecipe.dto.CategoryDto;
import arutala.backend.bookrecipe.dto.LevelDto;
import arutala.backend.bookrecipe.model.MyUserDetails;
import arutala.backend.bookrecipe.service.MasterService;
import arutala.backend.bookrecipe.service.UserDetailsServiceImpl;
import arutala.backend.bookrecipe.util.ResponseHandler;
import arutala.backend.bookrecipe.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book-recipe-masters")
@Slf4j
public class MasterController {
    @Autowired
    private MasterService masterService;

    @GetMapping("/category-option-lists")
    public ResponseEntity<Object> getCategories() {
        MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();

        List<CategoryDto> categories = masterService.getCategories();

        return ResponseHandler.ok(ResponseMessage.Success.DEFAULT, categories);
    }

    @GetMapping("/level-option-lists")
    public ResponseEntity<Object> getLevels() {
        MyUserDetails userDetails = UserDetailsServiceImpl.getUserDetailsFromContext();

        List<LevelDto> levels = masterService.getLevels();

        return ResponseHandler.ok(ResponseMessage.Success.DEFAULT, levels);
    }
}

package arutala.backend.bookrecipe.service;

import arutala.backend.bookrecipe.dto.CategoryDto;
import arutala.backend.bookrecipe.dto.LevelDto;
import arutala.backend.bookrecipe.model.Category;
import arutala.backend.bookrecipe.model.Level;
import arutala.backend.bookrecipe.repository.CategoryRepository;
import arutala.backend.bookrecipe.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LevelRepository levelRepository;

    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(CategoryDto::createFromModel).collect(Collectors.toList());
    }

    public List<LevelDto> getLevels() {
        return levelRepository.findAll().stream().map(LevelDto::createFromModel).collect(Collectors.toList());
    }

    public Level getLevelById(Integer id) {
        return levelRepository.findById(id).orElseThrow();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}

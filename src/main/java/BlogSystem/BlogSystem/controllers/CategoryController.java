package patikaOdev.BlogSystem.controllers;

import org.springframework.web.bind.annotation.*;
import patikaOdev.BlogSystem.dto.requests.AddCategoryRequest;
import patikaOdev.BlogSystem.dto.requests.UpdateCategoryRequest;
import patikaOdev.BlogSystem.dto.responses.GetAllCategoryResponse;
import patikaOdev.BlogSystem.dto.responses.GetAllUserResponse;
import patikaOdev.BlogSystem.entities.Category;
import patikaOdev.BlogSystem.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping("/getAll")
    public GetAllCategoryResponse getAll() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/add")
    public Category saveOneCategory(@RequestBody AddCategoryRequest newCategory) {
        return categoryService.saveOneCategory(newCategory);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteOneCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteOneCategoryById(categoryId);
    }
    @PutMapping("{categoryId}")
    public Category updateOneUser(@PathVariable  Long categoryId, @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return categoryService.updateOneCategory(categoryId, updateCategoryRequest);

    }
}

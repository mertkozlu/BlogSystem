package BlogSystem.BlogSystem.controllers;

import BlogSystem.BlogSystem.dto.requests.AddCategoryRequest;
import BlogSystem.BlogSystem.dto.requests.UpdateCategoryRequest;
import BlogSystem.BlogSystem.dto.responses.GetAllCategoryResponse;
import BlogSystem.BlogSystem.entities.Category;
import BlogSystem.BlogSystem.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public GetAllCategoryResponse getAll() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/add")
    public Category saveOneCategory(@RequestBody AddCategoryRequest newCategory) {
        return categoryService.saveOneCategory(newCategory);
    }

    @DeleteMapping("/delete/{categoryId}")
    public void deleteOneCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteOneCategoryById(categoryId);
    }

    @PutMapping("/update")
    public void updateOneUser(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
        this.categoryService.updateOneCategory(updateCategoryRequest);

    }
}

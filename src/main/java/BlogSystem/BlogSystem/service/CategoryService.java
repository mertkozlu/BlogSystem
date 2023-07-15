package BlogSystem.BlogSystem.service;

import BlogSystem.BlogSystem.dataAccess.CategoryRepository;
import BlogSystem.BlogSystem.dto.GetAllCategoryDto;
import BlogSystem.BlogSystem.dto.requests.AddCategoryRequest;
import BlogSystem.BlogSystem.dto.requests.UpdateCategoryRequest;
import BlogSystem.BlogSystem.dto.responses.GetAllCategoryResponse;
import BlogSystem.BlogSystem.entities.Category;
import BlogSystem.BlogSystem.exception.BusinessException;
import BlogSystem.BlogSystem.mapper.ModelMapperService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapperService modelMapperService;

    public CategoryService(CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }

    public GetAllCategoryResponse getAllCategories() {

        GetAllCategoryResponse response = new GetAllCategoryResponse();
        List<GetAllCategoryDto> dtos = categoryRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertCategoryGetAllCategoryDto)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(dtos)) {
            throw new BusinessException("Empty list");
        }
        response.setGetAllCategoryDto(dtos);
        response.setResultCode("1");
        response.setResultDescription("Success");

        return response;
    }

    public Category saveOneCategory(AddCategoryRequest newCategory) {
        Category category = modelMapperService.forRequest().map(newCategory, Category.class);
        category.setCreationDate(new Date());

        return categoryRepository.save(category);
    }

    public void deleteOneCategoryById(Long categoryId) {
        this.categoryRepository.deleteById(categoryId);
    }

    public void updateOneCategory(UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(updateCategoryRequest.getCategoryId()).orElseThrow(() -> new BusinessException("Category can not found"));
        Category categoryToUpdate = this.modelMapperService.forRequest().map(updateCategoryRequest, Category.class);
        categoryToUpdate.setCreationDate(category.getCreationDate());
        this.categoryRepository.save(categoryToUpdate);

    }

    private GetAllCategoryDto convertCategoryGetAllCategoryDto(Category category) {
        GetAllCategoryDto getAllCategoryDto = new GetAllCategoryDto();
        getAllCategoryDto.setCategoryId(category.getCategoryId());
        getAllCategoryDto.setCategoryName(category.getCategoryName());
        getAllCategoryDto.setCreationDate(category.getCreationDate());

        return getAllCategoryDto;
    }

}
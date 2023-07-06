package patikaOdev.BlogSystem.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import patikaOdev.BlogSystem.dataAccess.CategoryRepository;
import patikaOdev.BlogSystem.dto.GetAllCategoryDto;
import patikaOdev.BlogSystem.dto.GetAllCommentDto;
import patikaOdev.BlogSystem.dto.GetAllUsersDto;
import patikaOdev.BlogSystem.dto.requests.AddCategoryRequest;
import patikaOdev.BlogSystem.dto.requests.UpdateCategoryRequest;
import patikaOdev.BlogSystem.dto.responses.GetAllCategoryResponse;
import patikaOdev.BlogSystem.dto.responses.GetAllCommentResponse;
import patikaOdev.BlogSystem.entities.Category;
import patikaOdev.BlogSystem.entities.Comment;
import patikaOdev.BlogSystem.exception.BusinessException;
import patikaOdev.BlogSystem.mapper.ModelMapperService;

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
        return categoryRepository.save(category);
    }

    public void deleteOneCategoryById(Long categoryId) {
        this.categoryRepository.deleteById(categoryId);
    }

    public Category updateOneCategory(Long categoryId, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (Objects.nonNull(category)) {
            category.setCategoryName(updateCategoryRequest.getCategoryName());

            categoryRepository.save(category);
            return category;
        }

        throw new BusinessException("Category could not found");

    }

    private GetAllCategoryDto convertCategoryGetAllCategoryDto(Category category) {
        GetAllCategoryDto getAllCategoryDto = new GetAllCategoryDto();
        getAllCategoryDto.setCategoryId(category.getCategoryId());
        getAllCategoryDto.setCategoryName(category.getCategoryName());
        getAllCategoryDto.setCreationDate(new Date());

        return getAllCategoryDto;
    }

}
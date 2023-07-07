package BlogSystem.BlogSystem.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import BlogSystem.BlogSystem.dataAccess.CategoryRepository;
import BlogSystem.BlogSystem.dto.GetAllCategoryDto;
import BlogSystem.BlogSystem.dto.GetAllCommentDto;
import BlogSystem.BlogSystem.dto.GetAllUsersDto;
import BlogSystem.BlogSystem.dto.requests.AddCategoryRequest;
import BlogSystem.BlogSystem.dto.requests.UpdateCategoryRequest;
import BlogSystem.BlogSystem.dto.responses.GetAllCategoryResponse;
import BlogSystem.BlogSystem.dto.responses.GetAllCommentResponse;
import BlogSystem.BlogSystem.entities.Category;
import BlogSystem.BlogSystem.entities.Comment;
import BlogSystem.BlogSystem.exception.BusinessException;
import BlogSystem.BlogSystem.mapper.ModelMapperService;

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
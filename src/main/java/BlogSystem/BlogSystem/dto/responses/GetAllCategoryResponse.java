package BlogSystem.BlogSystem.dto.responses;

import BlogSystem.BlogSystem.dto.GetAllCategoryDto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllCategoryResponse extends BaseResponse {

    List<GetAllCategoryDto> getAllCategoryDto;

}

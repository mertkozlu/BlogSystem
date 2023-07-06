package patikaOdev.BlogSystem.dto.responses;

import lombok.Data;
import patikaOdev.BlogSystem.dto.GetAllCategoryDto;
import patikaOdev.BlogSystem.dto.GetAllCommentDto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class GetAllCategoryResponse extends BaseResponse {
    List<GetAllCategoryDto> getAllCategoryDto;

}

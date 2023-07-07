package BlogSystem.BlogSystem.dto.responses;

import BlogSystem.BlogSystem.dto.GetAllCommentDto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllCommentResponse extends BaseResponse {

    List<GetAllCommentDto> getAllCommentDto;

}

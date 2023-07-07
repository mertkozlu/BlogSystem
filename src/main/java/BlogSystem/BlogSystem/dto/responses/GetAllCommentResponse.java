package BlogSystem.BlogSystem.dto.responses;

import lombok.Data;
import BlogSystem.BlogSystem.dto.GetAllCommentDto;
import BlogSystem.BlogSystem.dto.GetAllPostDto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class GetAllCommentResponse extends BaseResponse {

    List<GetAllCommentDto> getAllCommentDto;

}

package BlogSystem.BlogSystem.dto.responses;

import lombok.Data;
import BlogSystem.BlogSystem.dto.GetAllPostDto;

import java.util.List;

@Data
public class GetAllPostResponse extends BaseResponse {

    List<GetAllPostDto> getAllPostDto;

}

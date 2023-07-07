package BlogSystem.BlogSystem.dto.responses;

import lombok.Data;
import BlogSystem.BlogSystem.dto.GetAllUsersDto;

import java.util.List;

@Data
public class GetAllUserResponse extends BaseResponse {

    List<GetAllUsersDto> getAllUsersDto;
}

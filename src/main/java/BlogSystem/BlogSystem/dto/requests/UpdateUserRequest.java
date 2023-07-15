package BlogSystem.BlogSystem.dto.requests;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private Long userId;

    private String userName;

    private String email;

    private Boolean isActive;
}

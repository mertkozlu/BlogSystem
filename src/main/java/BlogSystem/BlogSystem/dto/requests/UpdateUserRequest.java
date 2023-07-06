package patikaOdev.BlogSystem.dto.requests;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String userName;
    private String email;
    private boolean isActive = true;
}

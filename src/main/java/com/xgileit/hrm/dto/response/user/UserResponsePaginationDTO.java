package com.xgileit.hrm.dto.response.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xgileit.hrm.dto.response.user.UserResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponsePaginationDTO {
    List<UserResponse> userResponse;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    @Override
    public String toString() {
        return "UserResponsePaginationDTO{" + "userResponse=" + userResponse.toString() +
                ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalElements=" +
                totalElements + ", totalPages=" + totalPages + ", last=" + last + '}';
    }
}
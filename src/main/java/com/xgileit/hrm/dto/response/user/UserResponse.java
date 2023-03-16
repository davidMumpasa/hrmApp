package com.xgileit.hrm.dto.response.user;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String domain;
    private String phoneNumber;
    private ResponseStatusDTO status;
    private ResponseUserTypeDTO userType;
    private List<ResponseRoleDTO> roles;
    private List<ResponseCountryDTO> countryDetails ;
}

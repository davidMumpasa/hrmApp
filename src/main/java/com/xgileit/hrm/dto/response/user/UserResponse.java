package com.xgileit.hrm.dto.response.user;


import com.xgileit.hrm.persistance.entity.Country;
import com.xgileit.hrm.persistance.entity.Role;
import com.xgileit.hrm.persistance.entity.Status;
import com.xgileit.hrm.persistance.entity.UserType;
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
    private Status status;
    private List<Role> roles;
    private List<Country> countryDetails ;
}

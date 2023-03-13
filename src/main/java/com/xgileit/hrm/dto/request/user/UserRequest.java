package com.xgileit.hrm.dto.request.user;

import lombok.*;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private List<Integer> countryDetails ;
    private String phoneNumber;
    private String domain;
    private int statusId;
    private int userTypeId;
    private Boolean isGuestUser;
    private List<Integer> roleIds;
}

package com.xgileit.hrm.dto.response.user;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRoleDTO implements Serializable {
    private int roleId;
    private String roleName;
}

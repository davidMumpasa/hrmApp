package com.xgileit.hrm.dto.request.user;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestRoleDTO implements Serializable {
    private int roleId;
}

package com.xgileit.hrm.dto.response.user;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserTypeDTO implements Serializable {
    private int usertypeId;
    private String userTypeName;
}

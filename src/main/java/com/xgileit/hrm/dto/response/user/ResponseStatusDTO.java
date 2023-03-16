package com.xgileit.hrm.dto.response.user;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatusDTO implements Serializable {
    private int statusId;
    private String statusName;
}

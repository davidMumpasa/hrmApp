package com.xgileit.hrm.dto.request.user;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCountryDTO implements Serializable {
    private int countryId;
}

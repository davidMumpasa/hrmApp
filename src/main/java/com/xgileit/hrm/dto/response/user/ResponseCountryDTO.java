package com.xgileit.hrm.dto.response.user;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCountryDTO implements Serializable {
    private int countryId;
    private String countryCode;
    private String countryName;
}

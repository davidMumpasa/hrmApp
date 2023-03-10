package com.xgileit.hrm.persistance.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country_user_mapping")
public class CountryUserMapping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int countryUserId;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;
}

package com.xgileit.hrm.persistance.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int countryId;

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false, length = 255)
    private String countryName;


    @OneToMany(mappedBy = "country")
    private List<CountryUserMapping> countryUserMappings;
}

package com.xgileit.hrm.persistance.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int userId;

    @Column(length = 255)
    private String address;

    @Column(nullable = false, length = 255)
    private String firstName;

    @Column(nullable = false, length = 255)
    private String lastName;

    @Column(length = 255)
    private String username;

    @Column(nullable = false, length = 10)
    private String primaryEmail;

    @Column(nullable = false, length = 10)
    private String phoneNumber;

    @Column(length = 255)
    private String linkedIn;

    @Column(length = 255)
    private String domain;

    @Column(length = 255)
    private String createdBy;

    @Column(length = 255)
    private String modifyBy;

    @Column(length = 255)
    private Date modifyDate;

    @Column(length = 255)
    private Date createdTime;

    @Column(length = 255)
    private boolean isGuestUser;

    @Column(length = 255)
    private boolean isPasswordResetRequired;

    @ManyToOne
    @JoinColumn(name = "userTypeId", nullable = false)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CountryUserMapping> countryUserMappings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoleUserMapping> roleUserMappings;
}

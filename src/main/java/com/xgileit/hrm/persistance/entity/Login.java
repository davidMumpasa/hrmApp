package com.xgileit.hrm.persistance.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login")
public class Login implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int loginId;

    @Column(nullable = false, length = 68)
    private String password;

    @Column(nullable = false, length = 255)
    private String username;

    @Column(name = "logged_in_time", nullable = false)
    private Date loggedInTime;

    @Column(name = "expired_by", nullable = false)
    private Date expireBy;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

}

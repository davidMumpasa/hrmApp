package com.xgileit.hrm.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "roleUserMappings" })
@Entity
@Table(name = "roleId")
public class Role implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int roleId;

    @Column(nullable = false, length = 255)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<RoleUserMapping> roleUserMappings;


}

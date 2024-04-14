package com.projects.productservice.representinginheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
//@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}

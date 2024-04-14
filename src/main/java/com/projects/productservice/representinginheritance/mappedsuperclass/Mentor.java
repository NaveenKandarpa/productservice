package com.projects.productservice.representinginheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Entity(name = "msc_mentor")
public class Mentor extends User{
    private double mentorRating;
}
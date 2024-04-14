package com.projects.productservice.representinginheritance.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
@Entity(name = "jt_mentor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {
    private double mentorRating;
}
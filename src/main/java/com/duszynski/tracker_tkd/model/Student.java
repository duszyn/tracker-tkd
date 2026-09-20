package com.duszynski.tracker_tkd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Student {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Getter
    @Setter
    private String firstName;

    @NotNull
    @Column(nullable = false)
    @Getter
    @Setter
    private String lastName;

    @NotNull
    @Column(nullable = false)
    @Getter
    private LocalDate joinDate;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private StudentStatus status;

    public Student() {}

    public Student(String firstName, String lastName, LocalDate joinDate, StudentStatus status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.joinDate = joinDate;
        this.status = status;
    }
}

package com.duszynski.tracker_tkd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Getter @Setter
    private String topic;

    @NotNull
    @Column(nullable = false)
    @Getter @Setter
    private LocalDate date;

    public TrainingSession(){}

    public TrainingSession(String topic, LocalDate date) {
        this.topic = topic;
        this.date = date;
    }
}

package com.duszynski.tracker_tkd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @Getter
    private Student student;

    @ManyToOne
    @Getter
    private TrainingSession trainingSession;

    @Column(nullable = false)
    @Getter @Setter
    private boolean present;

    @Column
    @Min(1)
    @Max(10)
    @Getter @Setter
    private Integer engagement;

    public Attendance(TrainingSession trainingSession, Student student, boolean present, Integer engagement) {
        this.trainingSession = trainingSession;
        this.student = student;
        this.present = present;
        if(present) {
            this.engagement = engagement;
        } else {
            this.engagement = null;
        }
    }
}

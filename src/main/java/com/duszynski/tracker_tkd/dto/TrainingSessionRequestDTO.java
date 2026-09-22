package com.duszynski.tracker_tkd.dto;

import java.time.LocalDate;

public record TrainingSessionRequestDTO(
        String topic,
        LocalDate date
) {
}

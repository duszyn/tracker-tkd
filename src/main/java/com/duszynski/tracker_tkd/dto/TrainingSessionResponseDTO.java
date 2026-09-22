package com.duszynski.tracker_tkd.dto;

import java.time.LocalDate;

public record TrainingSessionResponseDTO(
        Long id,
        String topic,
        LocalDate date
        //Even though the DTO doesn't hide anything at the moment, if I change anything later on I want to change it here and not in couple of places.
) {
}

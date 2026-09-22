package com.duszynski.tracker_tkd.dto;

import com.duszynski.tracker_tkd.model.StudentStatus;

public record StudentResponseDTO(
        Long id,
        String firstName,
        String lastName,
        StudentStatus status
) {
}

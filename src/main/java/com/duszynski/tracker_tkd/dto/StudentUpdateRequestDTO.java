package com.duszynski.tracker_tkd.dto;

import com.duszynski.tracker_tkd.model.StudentStatus;

public record StudentUpdateRequestDTO(
        String firstName,
        String lastName,
        StudentStatus status
) {
}

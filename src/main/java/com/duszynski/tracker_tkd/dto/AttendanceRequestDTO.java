package com.duszynski.tracker_tkd.dto;

public record AttendanceRequestDTO(
        Long studentId,
        Long trainingSessionId,
        boolean present,
        Integer engagement
) {
}

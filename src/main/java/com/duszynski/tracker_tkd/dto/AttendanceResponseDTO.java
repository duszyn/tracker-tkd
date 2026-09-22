package com.duszynski.tracker_tkd.dto;

import java.time.LocalDate;

public record AttendanceResponseDTO(
        Long attendanceId,
        Long studentId,
        Long trainingSessionId,
        String studentFirstName,
        String studentLastName,
        LocalDate trainingSessionDate,
        boolean present,
        Integer engagement
) {
}

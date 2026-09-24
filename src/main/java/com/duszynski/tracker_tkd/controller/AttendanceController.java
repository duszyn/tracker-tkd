package com.duszynski.tracker_tkd.controller;

import com.duszynski.tracker_tkd.dto.AttendanceRequestDTO;
import com.duszynski.tracker_tkd.dto.AttendanceResponseDTO;
import com.duszynski.tracker_tkd.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public AttendanceResponseDTO createAttendance(@RequestBody AttendanceRequestDTO attendanceRequestDTO) {
        return attendanceService.createAttendance(attendanceRequestDTO);
    }

    @PutMapping("/{id}")
    public AttendanceResponseDTO updateAttendance(@PathVariable Long id, @RequestBody AttendanceRequestDTO attendanceRequestDTO) {
        return attendanceService.updateAttendance(id, attendanceRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }

    @GetMapping("/{id}")
    public AttendanceResponseDTO getAttendance(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }

    @GetMapping
    public List<AttendanceResponseDTO> getAttendances(@RequestParam Long studentId) {
        return attendanceService.findAllStudentAttendanceById(studentId);
    }
}

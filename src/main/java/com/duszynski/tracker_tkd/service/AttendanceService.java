package com.duszynski.tracker_tkd.service;

import com.duszynski.tracker_tkd.dto.AttendanceRequestDTO;
import com.duszynski.tracker_tkd.dto.AttendanceResponseDTO;
import com.duszynski.tracker_tkd.exception.AttendanceAlreadyExistsException;
import com.duszynski.tracker_tkd.exception.AttendanceNotFoundException;
import com.duszynski.tracker_tkd.model.Attendance;
import com.duszynski.tracker_tkd.model.Student;
import com.duszynski.tracker_tkd.model.TrainingSession;
import com.duszynski.tracker_tkd.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentService studentService;
    private final TrainingSessionService trainingSessionService;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentService studentService, TrainingSessionService trainingSessionService) {
        this.attendanceRepository = attendanceRepository;
        this.studentService = studentService;
        this.trainingSessionService = trainingSessionService;
    }

    public AttendanceResponseDTO createAttendance(AttendanceRequestDTO attendanceRequestDTO) {
        if(attendanceRepository.existsByStudentIdAndTrainingSessionId(attendanceRequestDTO.studentId(), attendanceRequestDTO.trainingSessionId())) { // Checking if student already has attendance on certain training session
            throw new AttendanceAlreadyExistsException("Attendance for student id " + attendanceRequestDTO.studentId() + " already exists at this training session!");
        }
        Attendance attendance = new Attendance(trainingSessionService.findTrainingSessionOrThrow(attendanceRequestDTO.trainingSessionId()), studentService.findStudentOrThrow(attendanceRequestDTO.studentId()),
                attendanceRequestDTO.present(), attendanceRequestDTO.engagement());
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return toAttendanceResponseDTO(savedAttendance);
    }

    public AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO attendanceRequestDTO) {
        Attendance attendance = findAttendanceByIdOrThrow(id);
        attendance.setStudent(studentService.findStudentOrThrow(attendanceRequestDTO.studentId()));
        attendance.setTrainingSession(trainingSessionService.findTrainingSessionOrThrow(attendanceRequestDTO.trainingSessionId()));
        attendance.setPresent(attendanceRequestDTO.present());
        if(!attendance.isPresent()) {
            attendance.setEngagement(null);
        } else {
            attendance.setEngagement(attendanceRequestDTO.engagement());
        }
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return toAttendanceResponseDTO(savedAttendance);
    }

    public void deleteAttendance(Long id) {
        if(attendanceRepository.existsById(id)) {
            attendanceRepository.deleteById(id);
        } else {
            throw new AttendanceNotFoundException("Attendance with id " + id + " not found!");
        }
    }

    public AttendanceResponseDTO getAttendanceById(Long id) {
        return toAttendanceResponseDTO(findAttendanceByIdOrThrow(id));
    }

    public List<AttendanceResponseDTO> findAllStudentAttendanceById(Long studentId) {
        return attendanceRepository.findAll().stream()
                .filter(attendance -> Objects.equals(attendance.getStudent().getId(), studentId))
                .map(this::toAttendanceResponseDTO)
                .toList();
    }

    public Attendance findAttendanceByIdOrThrow(Long attendanceId) {
        return attendanceRepository.findById(attendanceId).orElseThrow(() -> new AttendanceNotFoundException("Attendance with id " + attendanceId + " not found."));
    }

    public AttendanceResponseDTO toAttendanceResponseDTO(Attendance attendance) {
        Student student = attendance.getStudent();
        TrainingSession trainingSession = attendance.getTrainingSession();
        return new AttendanceResponseDTO(attendance.getId(), student.getId(), trainingSession.getId(), student.getFirstName(), student.getLastName(), trainingSession.getDate(), attendance.isPresent(), attendance.getEngagement());
    }
}

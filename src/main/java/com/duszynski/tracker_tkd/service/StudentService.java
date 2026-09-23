package com.duszynski.tracker_tkd.service;

import com.duszynski.tracker_tkd.dto.StudentCreateRequestDTO;
import com.duszynski.tracker_tkd.dto.StudentResponseDTO;
import com.duszynski.tracker_tkd.dto.StudentUpdateRequestDTO;
import com.duszynski.tracker_tkd.exception.StudentNotFoundException;
import com.duszynski.tracker_tkd.model.Student;
import com.duszynski.tracker_tkd.model.StudentStatus;
import com.duszynski.tracker_tkd.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponseDTO createStudent(StudentCreateRequestDTO studentCreateRequestDTO) {
        Student student = new Student(studentCreateRequestDTO.firstName(), studentCreateRequestDTO.lastName(), LocalDate.now(), StudentStatus.ACTIVE);
        Student savedStudent = studentRepository.save(student);
        return toResponseDTO(savedStudent);
    }

    public StudentResponseDTO updateStudent(Long id, StudentUpdateRequestDTO studentUpdateRequestDTO) {
        Student student = findStudentOrThrow(id);
        student.setFirstName(studentUpdateRequestDTO.firstName());
        student.setLastName(studentUpdateRequestDTO.lastName());
        student.setStatus(studentUpdateRequestDTO.status());
        Student updatedStudent = studentRepository.save(student);
        return toResponseDTO(updatedStudent);
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = findStudentOrThrow(id);
        return toResponseDTO(student);
    }

    public List<StudentResponseDTO> findAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<StudentResponseDTO> findAllStudentsByStatus(StudentStatus status) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getStatus() == status)
                .map(this::toResponseDTO)
                .toList();
    }

    private Student findStudentOrThrow(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found!"));
    }

    public void deleteStudent(Long id) {
        if(studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentNotFoundException("Student with id " + id + " not found!");
        }
    }
    private StudentResponseDTO toResponseDTO(Student student) {
        return new StudentResponseDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getJoinDate(), student.getStatus());
    }
}

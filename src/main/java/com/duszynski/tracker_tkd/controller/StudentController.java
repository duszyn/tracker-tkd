package com.duszynski.tracker_tkd.controller;

import com.duszynski.tracker_tkd.dto.StudentCreateRequestDTO;
import com.duszynski.tracker_tkd.dto.StudentResponseDTO;
import com.duszynski.tracker_tkd.dto.StudentUpdateRequestDTO;
import com.duszynski.tracker_tkd.model.StudentStatus;
import com.duszynski.tracker_tkd.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponseDTO addStudent(@RequestBody StudentCreateRequestDTO studentCreateRequestDTO) {
        return studentService.createStudent(studentCreateRequestDTO);
    }

    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(@PathVariable Long id, @RequestBody StudentUpdateRequestDTO studentUpdateRequestDTO) {
        return studentService.updateStudent(id, studentUpdateRequestDTO);
    }

    @GetMapping("/{id}")
    public StudentResponseDTO getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<StudentResponseDTO> getStudents(@RequestParam(required = false) StudentStatus status) {
        if(status == null) {
            return studentService.findAllStudents();
        }
        return studentService.findAllStudentsByStatus(status);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}

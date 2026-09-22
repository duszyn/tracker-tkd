package com.duszynski.tracker_tkd.repository;

import com.duszynski.tracker_tkd.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

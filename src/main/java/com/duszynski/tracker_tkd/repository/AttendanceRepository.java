package com.duszynski.tracker_tkd.repository;

import com.duszynski.tracker_tkd.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}

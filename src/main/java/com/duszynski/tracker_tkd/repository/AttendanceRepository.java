package com.duszynski.tracker_tkd.repositories;

import com.duszynski.tracker_tkd.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}

package com.duszynski.tracker_tkd.repositories;

import com.duszynski.tracker_tkd.model.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
}

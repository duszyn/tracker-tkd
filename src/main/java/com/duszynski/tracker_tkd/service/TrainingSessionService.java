package com.duszynski.tracker_tkd.service;

import com.duszynski.tracker_tkd.dto.TrainingSessionRequestDTO;
import com.duszynski.tracker_tkd.dto.TrainingSessionResponseDTO;
import com.duszynski.tracker_tkd.exception.TrainingSessionNotFoundException;
import com.duszynski.tracker_tkd.model.TrainingSession;
import com.duszynski.tracker_tkd.repository.TrainingSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;

    public TrainingSessionService(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    public TrainingSessionResponseDTO createTrainingSession(TrainingSessionRequestDTO trainingSessionRequestDTO) {
        TrainingSession trainingSession = new TrainingSession(trainingSessionRequestDTO.topic(), trainingSessionRequestDTO.date());
        TrainingSession savedTrainingSession = trainingSessionRepository.save(trainingSession);
        return toTrainingSessionResponseDTO(savedTrainingSession);
    }

    public TrainingSessionResponseDTO updateTrainingSession(Long id, TrainingSessionRequestDTO trainingSessionRequestDTO) {
        TrainingSession trainingSession = findTrainingSessionOrThrow(id);
        trainingSession.setTopic(trainingSessionRequestDTO.topic());
        trainingSession.setDate(trainingSessionRequestDTO.date());
        TrainingSession savedTrainingSession = trainingSessionRepository.save(trainingSession);
        return toTrainingSessionResponseDTO(savedTrainingSession);
    }

    public void deleteTrainingSession(Long id) {
        if(trainingSessionRepository.existsById(id)) {
            trainingSessionRepository.deleteById(id);
        } else {
            throw new TrainingSessionNotFoundException("TrainingSession with id " + id + " not found.");
        }
    }

    public TrainingSessionResponseDTO getTrainingSessionById(Long id) {
        TrainingSession trainingSession = findTrainingSessionOrThrow(id);
        return toTrainingSessionResponseDTO(trainingSession);
    }

    public List<TrainingSessionResponseDTO> getAllTrainingSessions() {
        return trainingSessionRepository.findAll().stream()
                .map(this::toTrainingSessionResponseDTO)
                .toList();
    }

    public TrainingSessionResponseDTO toTrainingSessionResponseDTO(TrainingSession trainingSession) {
        return new TrainingSessionResponseDTO(trainingSession.getId(), trainingSession.getTopic(), trainingSession.getDate());
    }

    public TrainingSession findTrainingSessionOrThrow(Long id) {
        return trainingSessionRepository.findById(id).orElseThrow(() -> new TrainingSessionNotFoundException("TrainingSession with id " + id + " not found!"));
    }
}

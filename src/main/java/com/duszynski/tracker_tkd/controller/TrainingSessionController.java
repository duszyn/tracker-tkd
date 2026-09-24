package com.duszynski.tracker_tkd.controller;

import com.duszynski.tracker_tkd.dto.TrainingSessionRequestDTO;
import com.duszynski.tracker_tkd.dto.TrainingSessionResponseDTO;
import com.duszynski.tracker_tkd.service.TrainingSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainingsession")
public class TrainingSessionController {

    private final TrainingSessionService trainingSessionService;

    public TrainingSessionController(TrainingSessionService trainingSessionService) {
        this.trainingSessionService = trainingSessionService;
    }

    @PostMapping
    public TrainingSessionResponseDTO createTrainingSession(@RequestBody TrainingSessionRequestDTO trainingSessionRequestDTO) {
        return trainingSessionService.createTrainingSession(trainingSessionRequestDTO);
    }

    @PutMapping("/{id}")
    public TrainingSessionResponseDTO updateTrainingSession(@PathVariable Long id, @RequestBody TrainingSessionRequestDTO trainingSessionRequestDTO) {
        return trainingSessionService.updateTrainingSession(id, trainingSessionRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainingSession(@PathVariable Long id) {
        trainingSessionService.deleteTrainingSession(id);
    }

    @GetMapping("/{id}")
    public TrainingSessionResponseDTO getTrainingSession(@PathVariable Long id) {
        return trainingSessionService.getTrainingSessionById(id);
    }

    @GetMapping
    public List<TrainingSessionResponseDTO> getTrainingSessions() {
        return trainingSessionService.getAllTrainingSessions();
    }
}

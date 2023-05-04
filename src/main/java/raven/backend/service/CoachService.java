package raven.backend.service;

import raven.backend.dto.CoachDto;

import java.util.List;

public interface CoachService {
    CoachDto create(CoachDto coachDto);

    List<CoachDto> getAll();

    CoachDto update(CoachDto coachDto, Integer coachId);

    void deleteById(Integer coachId);
}

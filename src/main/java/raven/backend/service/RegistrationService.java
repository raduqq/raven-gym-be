package raven.backend.service;

import raven.backend.dto.RegistrationDto;

import java.util.List;

public interface RegistrationService {
    RegistrationDto register(Integer studentId, Integer lessonId);

    List<RegistrationDto> getAll();

    void deregister(Integer studentId, Integer lessonId);
}

package raven.backend.service;

import raven.backend.dto.DisciplineDto;

import java.util.List;

public interface DisciplineService {
    DisciplineDto create(DisciplineDto disciplineDto);
    List<DisciplineDto> getAll();
    DisciplineDto update(DisciplineDto disciplineDto, Integer disciplineId);
    void deleteById(Integer disciplineId);
}

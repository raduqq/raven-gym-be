package raven.backend.service;

import raven.backend.dto.DisciplineDto;

import java.util.List;

public interface DisciplineService {
    DisciplineDto saveDiscipline(DisciplineDto disciplineDto);
    List<DisciplineDto> getDisciplines();
    DisciplineDto updateDiscipline(DisciplineDto disciplineDto, Integer disciplineId);
    void deleteDisciplineById(Integer disciplineId);
}

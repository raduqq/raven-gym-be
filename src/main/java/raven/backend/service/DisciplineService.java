package raven.backend.service;

import raven.backend.entity.Discipline;

import java.util.List;

public interface DisciplineService {
    Discipline saveDiscipline(Discipline discipline);
    List<Discipline> getDisciplines();
    Discipline updateDiscipline(Discipline discipline, Integer disciplineId);
    void deleteDisciplineById(Integer disciplineId);
}

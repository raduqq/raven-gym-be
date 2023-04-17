package raven.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raven.backend.entity.Discipline;
import raven.backend.repository.DisciplineRepository;

import java.util.List;
import java.util.Objects;

@Service
public class DisciplineServiceImpl implements DisciplineService {
    @Autowired
    private DisciplineRepository disciplineRepository;


    @Override
    public Discipline saveDiscipline(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    @Override
    public List<Discipline> getDisciplines() {
        return disciplineRepository.findAll();
    }

    @Override
    public Discipline updateDiscipline(Discipline discipline, Integer disciplineId) {
        Discipline dbDiscipline = disciplineRepository.findById(disciplineId).get();

        if (Objects.nonNull(discipline.getName()) && !"".equalsIgnoreCase(discipline.getName())) {
            dbDiscipline.setName(discipline.getName());
        }

        return disciplineRepository.save(dbDiscipline);
    }

    @Override
    public void deleteDisciplineById(Integer disciplineId) {
        disciplineRepository.deleteById(disciplineId);
    }
}

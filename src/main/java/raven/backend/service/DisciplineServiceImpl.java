package raven.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raven.backend.dto.DisciplineDto;
import raven.backend.entity.Discipline;
import raven.backend.mapper.DisciplineMapper;
import raven.backend.repository.DisciplineRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DisciplineServiceImpl implements DisciplineService {
    @Autowired
    private DisciplineRepository disciplineRepository;

    @Override
    public DisciplineDto saveDiscipline(DisciplineDto disciplineDto) {
        Discipline discipline = DisciplineMapper.INSTANCE.toDiscipline(disciplineDto);
        Discipline savedDiscipline = disciplineRepository.save(discipline);

        return DisciplineMapper.INSTANCE.toDisciplineDto(savedDiscipline);
    }

    @Override
    public List<DisciplineDto> getDisciplines() {

        return disciplineRepository.findAll()
                .stream()
                .map(DisciplineMapper.INSTANCE::toDisciplineDto)
                .collect(Collectors.toList());
    }

    @Override
    public DisciplineDto updateDiscipline(DisciplineDto disciplineDto, Integer disciplineId) {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new NoSuchElementException("No discipline found with id " + disciplineId));

        if (Objects.nonNull(discipline.getName()) && !"".equalsIgnoreCase(discipline.getName())) {
            discipline.setName(disciplineDto.name());
        }

        Discipline savedDiscipline = disciplineRepository.save(discipline);
        return DisciplineMapper.INSTANCE.toDisciplineDto(savedDiscipline);
    }

    @Override
    public void deleteDisciplineById(Integer disciplineId) {
        disciplineRepository.deleteById(disciplineId);
    }
}

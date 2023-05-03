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
    public DisciplineDto create(DisciplineDto disciplineDto) {
        Discipline discipline = DisciplineMapper.INSTANCE.toDiscipline(disciplineDto);
        Discipline savedDiscipline = disciplineRepository.save(discipline);

        return DisciplineMapper.INSTANCE.toDisciplineDto(savedDiscipline);
    }

    @Override
    public List<DisciplineDto> getAll() {

        return disciplineRepository.findAll()
                .stream()
                .map(DisciplineMapper.INSTANCE::toDisciplineDto)
                .collect(Collectors.toList());
    }

    @Override
    public DisciplineDto update(DisciplineDto disciplineDto, Integer disciplineId) {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new NoSuchElementException("No discipline found with ID " + disciplineId));

        if (Objects.nonNull(disciplineDto.name()) && !"".equalsIgnoreCase(disciplineDto.name())) {
            discipline.setName(disciplineDto.name());
        }

        Discipline savedDiscipline = disciplineRepository.save(discipline);
        return DisciplineMapper.INSTANCE.toDisciplineDto(savedDiscipline);
    }

    @Override
    public void deleteById(Integer disciplineId) {
        disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new NoSuchElementException("No discipline found with ID " + disciplineId));

        disciplineRepository.deleteById(disciplineId);
    }
}

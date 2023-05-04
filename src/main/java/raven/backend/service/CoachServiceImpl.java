package raven.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raven.backend.dto.CoachDto;
import raven.backend.entity.Coach;
import raven.backend.mapper.CoachMapper;
import raven.backend.repository.CoachRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachRepository coachRepository;

    @Override
    public CoachDto create(CoachDto coachDto) {
        Coach coach = CoachMapper.INSTANCE.toCoach(coachDto);
        Coach savedCoach = coachRepository.save(coach);

        return CoachMapper.INSTANCE.toCoachDto(savedCoach);
    }

    @Override
    public List<CoachDto> getAll() {

        return coachRepository.findAll()
                .stream()
                .map(CoachMapper.INSTANCE::toCoachDto)
                .collect(Collectors.toList());
    }

    @Override
    public CoachDto update(CoachDto coachDto, Integer coachId) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new NoSuchElementException("No coach found with ID " + coachId));

        coach.setName(coachDto.name());

        Coach savedCoach = coachRepository.save(coach);
        return CoachMapper.INSTANCE.toCoachDto(savedCoach);
    }

    @Override
    public void deleteById(Integer coachId) {
        coachRepository.findById(coachId)
                .orElseThrow(() -> new NoSuchElementException("No coach found with ID " + coachId));

        coachRepository.deleteById(coachId);
    }
}

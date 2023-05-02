package raven.backend.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raven.backend.dto.CoachDto;
import raven.backend.entity.Coach;

@Mapper
public interface CoachMapper {
    CoachMapper INSTANCE = Mappers.getMapper(CoachMapper.class);

    CoachDto toCoachDto(Coach coach);

    @InheritInverseConfiguration
    Coach toCoach(CoachDto coachDto);
}

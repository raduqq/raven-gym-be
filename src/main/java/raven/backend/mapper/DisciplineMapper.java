package raven.backend.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raven.backend.dto.DisciplineDto;
import raven.backend.entity.Discipline;

@Mapper
public interface DisciplineMapper {
    DisciplineMapper INSTANCE = Mappers.getMapper(DisciplineMapper.class);

    DisciplineDto toDisciplineDto(Discipline discipline);

    @InheritInverseConfiguration
    Discipline toDiscipline(DisciplineDto disciplineDto);
}

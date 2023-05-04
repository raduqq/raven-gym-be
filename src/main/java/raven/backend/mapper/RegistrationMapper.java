package raven.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raven.backend.dto.RegistrationDto;
import raven.backend.entity.Registration;

@Mapper
public interface RegistrationMapper {
    RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

    RegistrationDto toRegistrationDto(Registration registration);
}

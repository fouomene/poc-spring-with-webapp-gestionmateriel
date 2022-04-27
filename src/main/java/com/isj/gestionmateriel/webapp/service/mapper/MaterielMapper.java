package com.isj.gestionmateriel.webapp.service.mapper;

import com.isj.gestionmateriel.webapp.model.dto.MaterielDTO;
import com.isj.gestionmateriel.webapp.model.entities.Materiel;
import org.mapstruct.*;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy =  NullValueCheckStrategy.ALWAYS)
public interface MaterielMapper {

    Materiel toEntity(MaterielDTO materielDTO);

    MaterielDTO toDto(Materiel materiel);

    void copy(MaterielDTO materielDTO, @MappingTarget Materiel materiel);
}

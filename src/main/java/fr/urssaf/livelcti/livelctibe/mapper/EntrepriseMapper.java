package fr.urssaf.livelcti.livelctibe.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import fr.urssaf.livelcti.livelctibe.dto.livelcti.v3.EntrepriseType;

import fr.urssaf.livelcti.livelctibe.entity.EntrepriseEntity;



@Mapper(componentModel = "spring")
public interface EntrepriseMapper extends GenericMapper<EntrepriseType, EntrepriseEntity> {

    EntrepriseMapper INSTANCE = Mappers.getMapper(EntrepriseMapper.class);
    EntrepriseEntity toEntity(EntrepriseType dto);

    EntrepriseType toDTO(EntrepriseEntity entity);

    @Mapping(target = "id", ignore = false)
    EntrepriseEntity modify(@MappingTarget EntrepriseEntity entity, EntrepriseType entrepriseType);
}

package fr.urssaf.livelcti.livelctibe.mapper;

import org.mapstruct.Mapping;

public interface GenericMapper<T, E> {
    /**
     * Mapper permettant de passer d'une ressource DTO en entity.
     *
     * @param type DTO
     * @return l'entité contrat
     */
    @Mapping(target = "dateCreation", ignore = true)
    @Mapping(target = "utilisateur", ignore = true)
    E toEntity(T type);
}
